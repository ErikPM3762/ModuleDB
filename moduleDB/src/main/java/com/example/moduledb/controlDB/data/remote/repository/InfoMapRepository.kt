package com.example.moduledb.controlDB.data.remote.repository


import com.example.moduledb.controlDB.data.local.daos.MDbPOIsDao
import com.example.moduledb.controlDB.data.local.daos.MDbPORechargeDao
import com.example.moduledb.controlDB.data.local.daos.MDbVersionInfoDao
import com.example.moduledb.controlDB.data.local.entities.MDbPOIs
import com.example.moduledb.controlDB.data.local.entities.MDbPORecharge
import com.example.moduledb.controlDB.data.local.mapers.toPointsInterestList
import com.example.moduledb.controlDB.data.local.mapers.toPointsRechargeList
import com.example.moduledb.controlDB.data.remote.source.IInfoMapDataSource
import com.example.moduledb.controlDB.utils.NetResult
import com.example.moduledb.controlDB.utils.getGenericError
import com.example.moduledb.controlDB.utils.loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InfoMapRepository @Inject constructor(
    private val remoteDataSource: IInfoMapDataSource,
    private val pointInterestDao: MDbPOIsDao,
    private val pointRechargeDao: MDbPORechargeDao,
    private val mDbVersionInfoDao: MDbVersionInfoDao,
) {


    /**
     * Points Of Interest
     */
    suspend fun fetchPointInterestData(idLocalCompany: Int): Flow<NetResult<List<MDbPOIs>>> = flow {
        val localPointInterest = withContext(Dispatchers.IO) {
            pointInterestDao.getPointsInterestData()
        }
        if (localPointInterest.isNotEmpty()) {
            emit(NetResult.Success(localPointInterest))
        } else {
            remoteDataSource.getVersionTablePointInterest().loading()
                .flatMapConcat { versionResult ->
                    if (versionResult is NetResult.Success) {
                        val version = mDbVersionInfoDao.getPointInterestVersion()
                        val versionRemote = versionResult.data.updateVersion
                        if (version != versionRemote) {
                            remoteDataSource.getPointsInterest(idLocalCompany).loading()
                                .map { result ->
                                    if (result is NetResult.Success) {
                                        pointInterestDao.insertOrUpdate(result.data.toPointsInterestList())
                                    }
                                    result
                                }.flowOn(Dispatchers.IO)
                        } else {
                            flow { emit(NetResult.Success(emptyList())) }
                        }
                    } else {
                        flow { emit(NetResult.Error(getGenericError())) }
                    }
                }.flowOn(Dispatchers.IO).collect {
                    val response = when (it) {
                        is NetResult.Error -> it
                        is NetResult.Success -> NetResult.Success(it.data.toPointsInterestList())
                        else -> NetResult.Error(getGenericError())
                    }
                    emit(response)
                }
        }
    }

    /**
     * Obtener los puntos de recarga
     */
    suspend fun fetchPointOfRechargeData(idLocalCompany: Int): Flow<NetResult<List<MDbPORecharge>>> =
        flow {
            val localPointRecharge = withContext(Dispatchers.IO) {
                pointRechargeDao.getPointsRechargeData()
            }
            if (localPointRecharge.isNotEmpty()) {
                emit(NetResult.Success(localPointRecharge))
            } else {
                remoteDataSource.getVersionTablePointRecharge().loading()
                    .flatMapConcat { versionResult ->
                        if (versionResult is NetResult.Success) {
                            val version = mDbVersionInfoDao.getPointRechargeVersion()
                            val versionRemote = versionResult.data.updateVersion
                            if (version != versionRemote) {
                                remoteDataSource.getPointsRecharge(idLocalCompany).loading()
                                    .map { result ->
                                        if (result is NetResult.Success) {
                                            pointRechargeDao.insertOrUpdate(result.data.toPointsRechargeList())
                                        }
                                        result
                                    }.flowOn(Dispatchers.IO)
                            } else {
                                flow { emit(NetResult.Success(localPointRecharge)) }
                            }
                        } else {
                            flow { emit(NetResult.Error(getGenericError())) }
                        }
                    }.flowOn(Dispatchers.IO).collect {
                        val response = when (it) {
                            is NetResult.Error -> it
                            is NetResult.Success -> NetResult.Success(it.data.toPointsRechargeList())
                            else -> NetResult.Error(getGenericError())
                        }
                        emit(response)
                    }
            }
        }
}
