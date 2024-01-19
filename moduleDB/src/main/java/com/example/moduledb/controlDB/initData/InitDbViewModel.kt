package com.example.moduledb.controlDB.initData

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moduledb.controlDB.data.local.daos.MDbVersionInfoDao
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
import javax.inject.Inject

@HiltViewModel
class InitDbViewModel @Inject constructor(
    private val getPointsInterest: GetPointsInterest,
    private val getPointsRecharge: GetPointsRecharge,
    private val getMacroRegions: GetMacroRegions,
    private val getVersionTablePointInterest: GetVersionTablePointInterest,
    private val getVersionTablePointRecharge: GetVersionTablePointRecharge,
    private val mDbVersionInfoDao: MDbVersionInfoDao
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

    fun getMacroRegions(idLocalCompany: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getMacroRegions.invoke(idLocalCompany).collect() { resultVersion ->
                when (resultVersion) {
                    is NetResult.Success -> {
                        _pointsOfInterestAvailable.postValue(Event(Unit))
                    }
                    else -> {}
                }
            }
        }
    }
}