package com.example.moduledb.data.server.repository


import com.example.moduledb.controlDB.data.daos.PointsInterestDao
import com.example.moduledb.controlDB.data.mapers.toPointsInterestList
import com.example.moduledb.controlDB.data.models.PointsInterestResponse
import com.example.moduledb.data.server.source.InfoMapRemoteDataSource
import com.example.moduledb.utils.NetResult
import com.example.moduledb.utils.getGenericError
import com.example.moduledb.utils.loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class InfoMapRepository @Inject constructor(
    private val remoteDataSource: InfoMapRemoteDataSource,
    private val pointInterestDao: PointsInterestDao
) {


    suspend fun getPointsInterest(): Flow<NetResult<List<PointsInterestResponse>>> =
        remoteDataSource.getPointsInterest().loading().map { result ->
            if (result is NetResult.Success) {
                val pointsInterestList = result.data.toPointsInterestList()
                pointInterestDao.insertOrUpdate(pointsInterestList)
            }
            result
        }.loading().catch { error -> emit(NetResult.Error(getGenericError())) }
            .flowOn(Dispatchers.IO)
}