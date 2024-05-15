package com.example.moduledb.controlDB.data.remote.repository


import com.example.moduledb.controlDB.data.local.daos.MDbPOIsDao
import com.example.moduledb.controlDB.data.local.daos.MDbPORechargeDao
import com.example.moduledb.controlDB.data.local.daos.MDbVersionInfoDao
import com.example.moduledb.controlDB.data.local.entities.MDbPOIs
import com.example.moduledb.controlDB.data.local.entities.MDbPORecharge
import com.example.moduledb.controlDB.data.local.mapers.por.toPointsRechargeListInverted
import com.example.moduledb.controlDB.data.local.mapers.toPointsInterestList
import com.example.moduledb.controlDB.data.local.mapers.toPointsRechargeList
import com.example.services.data.response.pointsRecharge.PORecharge
import com.example.services.data.source.IInfoMapDataSource
import com.example.services.utils.NetResult
import com.example.services.utils.getGenericError
import com.example.services.utils.loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
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
     * Obtener los puntos de interes
     */
    suspend fun fetchPointInterestData(): Flow<NetResult<List<MDbPOIs>>> = flow {
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
                            remoteDataSource.getPointsInterest().loading().map { result ->
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
                }.flowOn(Dispatchers.IO).collect()
        }
    }


    /**
     * Obtener los puntos de recarga
     */
    suspend fun fetchPointOfRechargeData(): Flow<NetResult<List<PORecharge>>> = flow {

        val localPointRecharge = withContext(Dispatchers.IO) {
            pointRechargeDao.getPointsRechargeData()
        }
        val poRecharge = localPointRecharge.toPointsRechargeListInverted()
        if (localPointRecharge.isNotEmpty()) {
            emit(NetResult.Success(poRecharge))
        } else {
            remoteDataSource.getVersionTablePointRecharge().loading()
                .flatMapConcat { versionResult ->
                    if (versionResult is NetResult.Success) {
                        val version = mDbVersionInfoDao.getPointRechargeVersion()
                        val versionRemote = versionResult.data.updateVersion
                        if (version != versionRemote) {
                            remoteDataSource.getPointsRecharge().loading().map { result ->
                                if (result is NetResult.Success) {
                                    pointRechargeDao.insertOrUpdate(result.data.toPointsRechargeList())
                                }
                                result
                            }.flowOn(Dispatchers.IO)
                        } else {
                            flow { emit(NetResult.Success(poRecharge)) }
                        }
                    } else {
                        flow { emit(NetResult.Error(getGenericError())) }
                    }
                }.flowOn(Dispatchers.IO).collect()
        }
    }
}
