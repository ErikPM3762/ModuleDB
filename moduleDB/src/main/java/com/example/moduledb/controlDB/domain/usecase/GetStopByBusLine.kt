package com.example.moduledb.controlDB.domain.usecase

import com.example.moduledb.controlDB.data.remote.repository.StopsRepositoryImpl
import javax.inject.Inject

class GetStopByBusLine @Inject constructor(private val regionsRepository: StopsRepositoryImpl) {

    suspend fun invoke(busLineId: String): List<Any> =
        regionsRepository.getStopsByBuslineCrossingId(busLineId)
}