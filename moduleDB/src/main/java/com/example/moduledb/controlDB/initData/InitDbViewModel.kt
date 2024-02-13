package com.example.moduledb.controlDB.initData

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moduledb.controlDB.data.local.daos.MDbLinesByMacroRegionDao
import com.example.moduledb.controlDB.data.local.daos.MDbVersionInfoDao
import com.example.moduledb.controlDB.data.local.entities.MDbLinesByRegion
import com.example.moduledb.controlDB.data.local.entities.MDbListLines
import com.example.moduledb.controlDB.data.local.entities.MDbListStops
import com.example.moduledb.controlDB.data.local.entities.MDbMacroRegions
import com.example.moduledb.controlDB.data.local.entities.MDdRegions
import com.example.moduledb.controlDB.usecase.GetLinesByMacroRegion
import com.example.moduledb.controlDB.usecase.GetLinesByRegion
import com.example.moduledb.controlDB.usecase.GetMacroRegions
import com.example.moduledb.controlDB.usecase.GetPointsInterest
import com.example.moduledb.controlDB.usecase.GetPointsRecharge
import com.example.moduledb.controlDB.usecase.GetRegions
import com.example.moduledb.controlDB.usecase.GetStopByBusLine
import com.example.moduledb.controlDB.usecase.GetStopById
import com.example.moduledb.controlDB.usecase.GetStops
import com.example.moduledb.controlDB.usecase.routes.GetRoutesByIdLine
import com.example.moduledb.controlDB.utils.Event
import com.example.moduledb.controlDB.utils.NetResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class InitDbViewModel @Inject constructor(
    private val getPointsInterest: GetPointsInterest,
    private val getPointsRecharge: GetPointsRecharge,
    private val getMacroRegions: GetMacroRegions,
    private val getRegions: GetRegions,
    private val getLinesByMacroRegion: GetLinesByMacroRegion,
    private val getStops: GetStops,
    private val getLinesByRegion: GetLinesByRegion,
    private val mDbVersionInfoDao: MDbVersionInfoDao,
    private val mDbLinesList: MDbLinesByMacroRegionDao,
    private val getStopsByBusLine: GetStopByBusLine,
    private val getStopsById: GetStopById,
    private val getRoutesByIdLine: GetRoutesByIdLine
) : ViewModel() {

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
     * Obtenemos los puntos de interes solo si la version es diferente a la guardada en local.
     * Retorna los puntos de interes
     * Es aplicable para los siguientes negocios: Ahorrobus
     */
    fun getPointsInterest() {
        viewModelScope.launch(Dispatchers.IO) {
            getPointsInterest.invoke().collect { result ->
                when (result) {
                    is NetResult.Success -> {
                        _pointsOfInterestAvailable.postValue(Event(Unit))
                    }

                    else -> {}
                }
            }
        }
    }


    /**
     * Obtenemos los puntos de recarga solo si la version es diferente a la guardada en local.
     * Retorna Los puntos de recarga
     * Es aplicable para los siguientes negocios: Ahorrobus
     */
    fun getPointsRecharge() {
        viewModelScope.launch(Dispatchers.IO) {
            getPointsRecharge.invoke().collect { result ->
                when (result) {
                    is NetResult.Success -> {
                        Log.e("***", "Total de POR ${result.data.size}")
                        _pointsOfRechargeAvailable.postValue(Event(Unit))
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
                        val macroRegions = result.data as List<MDbMacroRegions>
                        for (macroRegion in macroRegions) {
                            viewModelScope.launch {
                                getLinesByMacroRegion.invoke(
                                    idLocalCompany, macroRegion.idMacroRegion
                                ).collect() { resultListLines ->
                                    when (resultListLines) {
                                        is NetResult.Success -> {
                                            val listLinesByMRegions =
                                                resultListLines.data as List<MDbListLines>
                                            Log.e(
                                                "El tamaño de Las lineas es : ",
                                                "${listLinesByMRegions.size}"
                                            )
                                        }

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
                        val regions = result.data as List<MDdRegions>
                        Log.e("Size Regiones es : ", "${regions.size}")
                        for (region in regions) {
                            viewModelScope.launch {
                                getLinesByRegion.invoke(idLocalCompany, region.idRegion)
                                    .collect() { resultListLines ->
                                        when (resultListLines) {
                                            is NetResult.Success -> {
                                                val listByregions =
                                                    resultListLines.data as List<MDbLinesByRegion>
                                                Log.e("Size lineas es : ", "${listByregions.size}")
                                            }

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

    fun fetchStopsByBuslineCrossingId(buslineCrossingId: String) {
        viewModelScope.launch {
            val stopListResult = getStopsByBusLine.invoke(buslineCrossingId)
            val stopList: List<MDbListStops> = stopListResult as List<MDbListStops>

            for (stop in stopList) {
                Log.e("LIST_STOP_BY_ID", stop.toString())
            }
        }

        viewModelScope.launch {
            val stopListResult = getStopsById.invoke(1)
            val stop: MDbListStops? = stopListResult
            Log.e("STOP_BY_ID", stop.toString())
        }
    }

    fun getRoutes(idLocalCompany: String, idLine: String) = viewModelScope.launch {
        val TAG = "getRoutes"
        getRoutesByIdLine(idLocalCompany, idLine).collectLatest {
            when (it) {
                is NetResult.Success -> {
                    Log.d(TAG, "getRoutes: ${it.data}")
                }
                is NetResult.Error -> Log.e(TAG, "getRoutes: error in request")
                else -> {
                    //Unused function
                }
            }
        }

    }
}