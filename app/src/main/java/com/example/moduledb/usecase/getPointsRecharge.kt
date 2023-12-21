package com.example.moduledb.usecase

import com.example.moduledb.controlDB.data.models.MDbPOIsResponse
import com.example.moduledb.controlDB.data.models.MDbPORechargeResponse
import com.example.moduledb.data.server.repository.InfoMapRepository
import com.example.moduledb.utils.NetResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class getPointsRecharge @Inject constructor(private val infoMapRepository: InfoMapRepository){

    suspend fun invoke() : Flow<NetResult<List<MDbPORechargeResponse>>> =
        infoMapRepository.getPointsRecharge()
}