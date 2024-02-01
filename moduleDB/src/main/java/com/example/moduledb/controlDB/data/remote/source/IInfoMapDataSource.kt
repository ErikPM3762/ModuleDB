package com.example.moduledb.controlDB.data.remote.source

import com.example.moduledb.controlDB.data.remote.models.MDbPOIsResponse
import com.example.moduledb.controlDB.data.remote.models.MDbPORechargeResponse
import com.example.moduledb.controlDB.data.remote.models.MDbVTPointInterestResponse
import com.example.moduledb.controlDB.data.remote.models.MDbVTPointRechargeResponse
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.flow.Flow

interface IInfoMapDataSource {

    suspend fun getPointsInterest(): Flow<NetResult<List<MDbPOIsResponse>>>
    suspend fun getPointsRecharge(): Flow<NetResult<List<MDbPORechargeResponse>>>
    suspend fun getVersionTablePointInterest(): Flow<NetResult<MDbVTPointInterestResponse>>
    suspend fun getVersionTablePointRecharge(): Flow<NetResult<MDbVTPointRechargeResponse>>
}