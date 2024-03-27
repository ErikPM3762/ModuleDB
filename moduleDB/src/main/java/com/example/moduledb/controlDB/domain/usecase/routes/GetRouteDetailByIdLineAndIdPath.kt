package com.example.moduledb.controlDB.domain.usecase.routes

import com.example.moduledb.controlDB.data.local.entities.MDbLinesDetail
import com.example.moduledb.controlDB.data.remote.repository.LinesRepository
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRouteDetailByIdLineAndIdPath @Inject constructor(private val repository: LinesRepository) {

    operator fun invoke(
        idLocalCompany: Int,
        idBusline: String,
        pathIdBusLine: String,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<NetResult<List<MDbLinesDetail>>> =
        repository.getRouteDetail(idLocalCompany, idBusline, pathIdBusLine).flowOn(dispatcher)
}