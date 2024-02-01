package com.example.moduledb.controlDB.initData

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moduledb.controlDB.data.local.daos.MDbLinesByMacroRegionDao
import com.example.moduledb.controlDB.data.local.daos.MDbLinesByRegionDao
import com.example.moduledb.controlDB.data.local.daos.MDbMacroRegionsDao
import com.example.moduledb.controlDB.data.local.daos.MDbVersionInfoDao
import com.example.moduledb.controlDB.data.local.entities.MDbListLines
import com.example.moduledb.controlDB.data.local.entities.MDbListStops
import com.example.moduledb.controlDB.data.local.entities.MDbMacroRegions
import com.example.moduledb.controlDB.usecase.GetLinesByMacroRegion
import com.example.moduledb.controlDB.usecase.GetLinesByRegion
import com.example.moduledb.controlDB.usecase.GetMacroRegions
import com.example.moduledb.controlDB.usecase.GetPointsInterest
import com.example.moduledb.controlDB.usecase.GetPointsRecharge
import com.example.moduledb.controlDB.usecase.GetRegions
import com.example.moduledb.controlDB.usecase.GetStops
import com.example.moduledb.controlDB.usecase.GetVersionTablePointInterest
import com.example.moduledb.controlDB.usecase.GetVersionTablePointRecharge
import com.example.moduledb.controlDB.utils.Event
import com.example.moduledb.controlDB.utils.NetResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class InitDbViewModel @Inject constructor(
    private val getPointsInterest: GetPointsInterest,
    private val getPointsRecharge: GetPointsRecharge,
    private val getMacroRegions: GetMacroRegions,
    private val getRegions: GetRegions,
    private val getVersionTablePointInterest: GetVersionTablePointInterest,
    private val getVersionTablePointRecharge: GetVersionTablePointRecharge,
    private val getLinesByMacroRegion: GetLinesByMacroRegion,
    private val getStops: GetStops,
    private val getLinesByRegion: GetLinesByRegion,
    private val mDbVersionInfoDao: MDbVersionInfoDao,
    private val mDbLinesList: MDbLinesByMacroRegionDao,
    private val mDbLinesByRegion: MDbLinesByRegionDao,
    private val mDbMacroRegionList: MDbMacroRegionsDao
) :
    ViewModel() {

    private val _pointsOfInterestAvailable = MutableLiveData<Event<Unit>>()
    val pointsOfInterestAvailable: LiveData<Event<Unit>> get() = _pointsOfInterestAvailable

    private val _pointsOfInterestNotAvailable = MutableLiveData<Event<Unit>>()
    val pointsOfInterestNotAvailable: LiveData<Event<Unit>> get() = _pointsOfInterestNotAvailable

    private val _pointsOfRechargeAvailable = MutableLiveData<Event<Unit>>()
    val pointsOfRechargeAvailable: LiveData<Event<Unit>> get() = _pointsOfRechargeAvailable

    private val _pointsOfRechargeNotAvailable = MutableLiveData<Event<Unit>>()
    val pointsOfRechargeNotAvailable: LiveData<Event<Unit>> get() = _pointsOfRechargeNotAvailable

    private val _mdbListLines = MutableLiveData<List<MDbListLines>>()
    val mdbListLines: LiveData<List<MDbListLines>> get() = _mdbListLines

    private val _mdbListMacroRegion = MutableLiveData<List<MDbMacroRegions>>()
    val mdbListMacroRegion: LiveData<List<MDbMacroRegions>> get() = _mdbListMacroRegion

    private val _mdbListAllLines = MutableLiveData<List<MDbListLines>>()
    val mdbListAllLines: LiveData<List<MDbListLines>> get() = _mdbListAllLines

    private val _mdbListStops = MutableLiveData<List<MDbListStops>>()
    val mdbListStops: LiveData<List<MDbListStops>> get() = _mdbListStops

    init {
        viewModelScope.launch(Dispatchers.IO) {
            mDbVersionInfoDao.insertOrUpdatePointInterestAndRecharge(0, 0)
        }
    }

    /**
     * Funcionalidad abierta para ser utilizada por medio de la instancia del viewModel de manera externa
     * Obtenemos los puntos de interes solo si la version es diferente a la guardada en local.
     */
    fun getPointsInterest() {
        viewModelScope.launch(Dispatchers.IO) {
            getVersionTablePointInterest.invoke().collect() { resultVersion ->
                when (resultVersion) {
                    is NetResult.Success -> {
                        val version = mDbVersionInfoDao.getPointInterestVersion()
                        val versionRemote = resultVersion.data.updateVersion
                        if (version != versionRemote) {
                            mDbVersionInfoDao.updatePointInterest(versionRemote)
                            getPointsInterest.invoke().collect { result ->
                                _pointsOfInterestAvailable.postValue(Event(Unit))
                            }
                        } else {
                            _pointsOfInterestAvailable.postValue(Event(Unit))
                        }
                    }

                    else -> {}
                }

            }
        }
    }

    /**
     * Funcionalidad abierta para ser utilizada por medio de la instancia del viewModel de manera externa
     * Obtenemos los puntos de recarga solo si la version es diferente a la guardada en local.
     */
    fun getPointsRecharge() {
        viewModelScope.launch(Dispatchers.IO) {
            getVersionTablePointRecharge.invoke().collect() { resultVersion ->
                when (resultVersion) {
                    is NetResult.Success -> {
                        val version = mDbVersionInfoDao.getPointRechargeVersion()
                        val versionRemote = resultVersion.data.updateVersion
                        if (version != versionRemote) {
                            getPointsRecharge.invoke().collect { result ->
                                mDbVersionInfoDao.updatePointRecharge(versionRemote)
                                _pointsOfRechargeAvailable.postValue(Event(Unit))
                            }
                        } else {
                            _pointsOfRechargeAvailable.postValue(Event(Unit))
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    /**
     * Funcionalidad abierta para ser utilizada por medio de la instancia del viewModel de manera externa
     * Obtener las macro regiones e iterar sobre la respuesta para guardar la lista de lineas por macroRegion
     */
    fun getMacroRegions(idLocalCompany: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getMacroRegions.invoke(idLocalCompany).collect() { result ->
                when (result) {
                    is NetResult.Success -> {
                        mDbLinesList.deleteAll()
                        val macroRegions = result.data
                        for (macroRegion in macroRegions) {
                            viewModelScope.launch {
                                getLinesByMacroRegion.invoke(
                                    idLocalCompany,
                                    macroRegion.idMacroRegion
                                )
                                    .collect() { resultListLines ->
                                        when (resultListLines) {
                                            is NetResult.Success -> {}

                                            else -> {
                                                // Manejar el error si es necesario
                                            }
                                        }
                                        _pointsOfInterestAvailable.postValue(Event(Unit))
                                    }
                            }
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    /**
     * Funcionalidad abierta para ser utilizada por medio de la instancia del viewModel de manera externa
     * Obtener las regiones e iterar sobre la respuesta para guardar la lista de lineas por Region
     */
    fun getRegions(idLocalCompany: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getRegions.invoke(idLocalCompany).collect() { result ->
                when (result) {
                    is NetResult.Success -> {
                        mDbLinesByRegion.deleteAll()
                        val regions = result.data
                        for (region in regions) {
                            viewModelScope.launch {
                                getLinesByRegion.invoke(idLocalCompany, region.idRegion)
                                    .collect() { resultListLines ->
                                        when (resultListLines) {
                                            is NetResult.Success -> {}

                                            else -> {
                                                // Manejar el error si es necesario
                                            }
                                        }
                                        _pointsOfInterestAvailable.postValue(Event(Unit))
                                    }
                            }
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    /**
     * Funcion publica para obtener el listado de lineas por MacroRegion
     */
    fun getLinesByMacroRegionDb(idMacroRegion: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = mDbLinesList.getMDbListLinesById(idMacroRegion.toString())
            withContext(Dispatchers.Main) {
                _mdbListLines.value = result
            }
        }
    }

    /**
     * Funcion publica para obtener el listado de macroRegiones
     */
    fun getMacroRegionListDb() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = mDbMacroRegionList.getMacroRegions()
            withContext(Dispatchers.Main) {
                _mdbListMacroRegion.value = result
            }
        }
    }

    /**
     * Funcion publica para obtener el listado del total de lineas generada por las macroRegiones
     */
    fun getAllListLinesGeneratedByMacroRegion() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = mDbLinesList.getAllMDbListLines()
            withContext(Dispatchers.Main) {
                _mdbListAllLines.value = result
            }
        }
    }

    /**
     * Funcionalidad abierta para ser utilizada por medio de la instancia del viewModel de manera externa
     * Obtenemos la lista de las paradas de Oracle
     */
    fun getStops(idLocalCompany: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getStops.invoke(idLocalCompany).collect() { result ->
                when (result) {
                    is NetResult.Success -> {
                        val mdbListStops = result.data as List<MDbListStops>
                        _mdbListStops.postValue(mdbListStops)
                    }

                    else -> {}
                }
            }
        }
    }
}