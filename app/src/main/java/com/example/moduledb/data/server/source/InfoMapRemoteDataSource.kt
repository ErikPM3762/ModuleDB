package com.example.moduledb.data.server.source

import com.example.moduledb.controlDB.data.models.PointsInterestResponse
import com.example.moduledb.utils.NetResult
import kotlinx.coroutines.flow.Flow

interface InfoMapRemoteDataSource {

    suspend fun getPointsInterest(): Flow<NetResult<List<PointsInterestResponse>>>
}