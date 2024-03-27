package com.example.moduledb.controlDB.domain.usecase

import com.example.moduledb.controlDB.data.local.entities.MDbListStops
import com.example.moduledb.controlDB.data.remote.repository.StopsRepositoryImpl
import javax.inject.Inject

class GetStopById @Inject constructor(private val stopRepository: StopsRepositoryImpl) {

    suspend fun invoke(idBusStop: Int): MDbListStops? {
        return stopRepository.getStopById(idBusStop)
    }
}