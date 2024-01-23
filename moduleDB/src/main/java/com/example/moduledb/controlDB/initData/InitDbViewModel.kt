package com.example.moduledb.controlDB.initData

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moduledb.controlDB.data.local.daos.MDbListLinesDao
import com.example.moduledb.controlDB.data.local.daos.MDbMacroRegionsDao
import com.example.moduledb.controlDB.data.local.daos.MDbVersionInfoDao
import com.example.moduledb.controlDB.data.local.entities.MDbListLines
import com.example.moduledb.controlDB.data.local.entities.MDbMacroRegions
import com.example.moduledb.controlDB.usecase.GetListLines
import com.example.moduledb.controlDB.usecase.GetMacroRegions
import com.example.moduledb.controlDB.usecase.GetPointsInterest
import com.example.moduledb.controlDB.usecase.GetPointsRecharge
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
    private val getVersionTablePointInterest: GetVersionTablePointInterest,
    private val getVersionTablePointRecharge: GetVersionTablePointRecharge,
    private val getListLines: GetListLines,
    private val mDbVersionInfoDao: MDbVersionInfoDao,
    private val mDbLinesList: MDbListLinesDao,
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

    init {
        viewModelScope.launch(Dispatchers.IO) {
            mDbVersionInfoDao.insertOrUpdatePointInterestAndRecharge(0, 0)
        }
    }

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

    fun getMacroRegions(idLocalCompany: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getMacroRegions.invoke(idLocalCompany).collect() { result ->
                when (result) {
                    is NetResult.Success -> {
                        mDbLinesList.deleteAll()
                        val macroRegions = result.data
                        for (macroRegion in macroRegions) {
                            viewModelScope.launch {
                                getListLines.invoke(idLocalCompany, macroRegion.idMacroRegion)
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

    fun getLineDb(idMacroRegion: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = mDbLinesList.getMDbListLinesById(idMacroRegion.toString())
            withContext(Dispatchers.Main) {
                _mdbListLines.value = result
            }
        }
    }

    fun getMacroRegionListDb() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = mDbMacroRegionList.getMacrpoRegions()
            withContext(Dispatchers.Main) {
                _mdbListMacroRegion.value = result
            }
        }
    }

    fun getListLinesDb() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = mDbLinesList.getAllMDbListLines()
            withContext(Dispatchers.Main) {
                _mdbListAllLines.value = result
            }
        }
    }
}