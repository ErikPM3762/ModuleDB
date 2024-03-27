package com.example.moduledb.controlDB.domain.usecase

import com.example.moduledb.controlDB.data.remote.repository.StopsRepositoryImpl
import com.example.moduledb.controlDB.data.remote.response.teroicByStop.BusStopResponse
import com.example.moduledb.controlDB.domain.models.MDBTheoricByTypeStop
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTheoricByTypeStop @Inject constructor(
    private val repository: StopsRepositoryImpl) {

    suspend operator fun invoke(idLocalCompany : Int, idBusLine: String, tripCode: String) : Flow<NetResult<Any>> =
        repository.getTheoricByTypeStopImpl(idLocalCompany, idBusLine, tripCode)
}