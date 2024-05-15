package com.example.moduledb.controlDB.domain.usecase

import com.example.moduledb.controlDB.data.local.entities.BusStopEntity
import com.example.moduledb.controlDB.data.remote.repository.StopsRepositoryImpl
import javax.inject.Inject

class GetTheoricByIdStop @Inject constructor(
    private val repository: StopsRepositoryImpl) {

    suspend operator fun invoke(idBusStop: String, idLineGenerate: String, tripCode: String) : BusStopEntity? =
        repository.getTheoricStopById(idBusStop, idLineGenerate, tripCode)
}