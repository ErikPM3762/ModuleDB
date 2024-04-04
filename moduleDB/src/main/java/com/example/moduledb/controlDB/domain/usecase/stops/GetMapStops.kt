package com.example.moduledb.controlDB.domain.usecase.stops

import com.example.moduledb.controlDB.data.remote.repository.StopsRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMapStops @Inject constructor(private val repository: StopsRepositoryImpl) {

    operator fun invoke(
        idLocalCompany: Int,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) = repository.getMapStops(idLocalCompany).flowOn(dispatcher)
}