package com.example.moduledb.controlDB.data.remote.repository


import com.example.moduledb.controlDB.data.local.daos.MDbPOIsDao
import com.example.moduledb.controlDB.data.local.daos.MDbPORechargeDao
import com.example.moduledb.controlDB.data.mapers.toPointsInterestList
import com.example.moduledb.controlDB.data.mapers.toPointsRechargeList
import com.example.moduledb.controlDB.data.models.MDbPOIsResponse
import com.example.moduledb.controlDB.data.models.MDbPORechargeResponse
import com.example.moduledb.controlDB.data.models.MDbVTPointInterestResponse
import com.example.moduledb.controlDB.data.models.MDbVTPointRechargeResponse
import com.example.moduledb.controlDB.data.remote.source.IInfoMapDataSource
import com.example.moduledb.controlDB.utils.NetResult
import com.example.moduledb.controlDB.utils.getGenericError
import com.example.moduledb.controlDB.utils.loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class InfoMapRepository @Inject constructor(
    private val remoteDataSource: IInfoMapDataSource,
    private val pointInterestDao: MDbPOIsDao,
    private val pointRechargeDao: MDbPORechargeDao
) {


    suspend fun getPointsInterest(): Flow<NetResult<List<MDbPOIsResponse>>> =
        remoteDataSource.getPointsInterest().loading().map { result ->
            if (result is NetResult.Success) {
                pointInterestDao.insertOrUpdate(result.data.toPointsInterestList())
            }
            result
        }.loading().catch { error -> emit(NetResult.Error(getGenericError())) }
            .flowOn(Dispatchers.IO)

    suspend fun getPointsRecharge(): Flow<NetResult<List<MDbPORechargeResponse>>> =
        remoteDataSource.getPointsRecharge().loading().map { result ->
            if (result is NetResult.Success) {
                val pointsInterestList = result.data.toPointsRechargeList()
                pointRechargeDao.insertOrUpdate(pointsInterestList)
            }
            result
        }.loading().catch { error -> emit(NetResult.Error(getGenericError())) }
            .flowOn(Dispatchers.IO)

    suspend fun getVersionTablePointInterest(): Flow<NetResult<MDbVTPointInterestResponse>> =
        remoteDataSource.getVersionTablePointInterest().loading().map { result ->
            if (result is NetResult.Success) {}
            result
        }.loading().catch { error -> emit(NetResult.Error(getGenericError())) }
            .flowOn(Dispatchers.IO)

    suspend fun getVersionTablePointRecharge(): Flow<NetResult<MDbVTPointRechargeResponse>> =
        remoteDataSource.getVersionTablePointRecharge().loading().map { result ->
            if (result is NetResult.Success) {}
            result
        }.loading().catch { error -> emit(NetResult.Error(getGenericError())) }
            .flowOn(Dispatchers.IO)
}