package com.example.moduledb.data.server.source

import com.example.moduledb.controlDB.data.models.MDbPOIsResponse
import com.example.moduledb.controlDB.data.models.MDbPORechargeResponse
import com.example.moduledb.utils.NetResult
import kotlinx.coroutines.flow.Flow

interface InfoMapRemoteDataSource {

    suspend fun getPointsInterest(): Flow<NetResult<List<MDbPOIsResponse>>>
    suspend fun getPointsRecharge(): Flow<NetResult<List<MDbPORechargeResponse>>>
}