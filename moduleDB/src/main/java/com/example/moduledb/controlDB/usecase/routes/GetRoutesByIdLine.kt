package com.example.moduledb.controlDB.usecase.routes

import com.example.moduledb.controlDB.data.remote.repository.RegionsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRoutesByIdLine @Inject constructor(private val regionsRepository: RegionsRepository) {

    operator fun invoke(
        idLocalCompany: String,
        idLine: String,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) = regionsRepository.getRoutesByIdLine(idLocalCompany, idLine).flowOn(dispatcher)
}