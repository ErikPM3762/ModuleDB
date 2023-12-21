package com.example.moduledb.data.server.repository


import android.util.Log
import com.example.moduledb.controlDB.data.daos.MDbPOIsDao
import com.example.moduledb.controlDB.data.daos.MDbPORechargeDao
import com.example.moduledb.controlDB.data.mapers.toPointsInterestList
import com.example.moduledb.controlDB.data.mapers.toPointsRechargeList
import com.example.moduledb.controlDB.data.models.MDbPOIsResponse
import com.example.moduledb.controlDB.data.models.MDbPORechargeResponse
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
    private val pointInterestDao: MDbPOIsDao,
    private val pointRechargeDao: MDbPORechargeDao
) {


    suspend fun getPointsInterest(): Flow<NetResult<List<MDbPOIsResponse>>> =
        remoteDataSource.getPointsInterest().loading().map { result ->
            if (result is NetResult.Success) {
                Log.e("TEST-INTERES-AA",result.data.toString())
                pointInterestDao.insertOrUpdate(result.data.toPointsInterestList())
            }
            result
        }.loading()

    suspend fun getPointsRecharge(): Flow<NetResult<List<MDbPORechargeResponse>>> =
        remoteDataSource.getPointsRecharge().loading().map { result ->
            if (result is NetResult.Success) {
                val pointsInterestList = result.data.toPointsRechargeList()
                pointRechargeDao.insertOrUpdate(pointsInterestList)
            }
            result
        }.loading()
}