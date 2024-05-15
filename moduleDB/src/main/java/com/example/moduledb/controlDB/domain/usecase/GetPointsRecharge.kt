package com.example.moduledb.controlDB.domain.usecase

import com.example.moduledb.controlDB.data.local.entities.MDbPORecharge
import com.example.moduledb.controlDB.data.remote.repository.InfoMapRepository
import com.example.services.data.response.pointsRecharge.PORecharge
import com.example.services.utils.NetResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPointsRecharge @Inject constructor(private val infoMapRepository: InfoMapRepository){

    suspend fun invoke() : Flow<NetResult<List<PORecharge>>> =
        infoMapRepository.fetchPointOfRechargeData()
}