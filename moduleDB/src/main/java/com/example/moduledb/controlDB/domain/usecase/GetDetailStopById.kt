package com.example.moduledb.controlDB.domain.usecase

import com.example.moduledb.controlDB.domain.models.MDBDetailStop
import com.example.moduledb.controlDB.domain.repository.StopsRepository
import com.example.services.data.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDetailStopById @Inject constructor(
    private val repository: StopsRepository
    ) {

    operator fun invoke(
        idLocalCompany: Int,
        idBusStop: String,
    ): Flow<DataResult<MDBDetailStop, Exception>> = flow {
        emit(repository.getDetailStopsById(idLocalCompany, idBusStop))
    }
}