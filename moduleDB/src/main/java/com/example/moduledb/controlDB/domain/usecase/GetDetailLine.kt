package com.example.moduledb.controlDB.domain.usecase

import com.example.moduledb.controlDB.data.remote.repository.LinesRepository
import com.example.services.data.response.lines.LinesDetail
import com.example.services.utils.NetResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetDetailLine @Inject constructor(private val repository: LinesRepository) {

    operator fun invoke(
        idLocalCompany: Int,
        idBusline: String,
        state: String,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<NetResult<List<LinesDetail>>> =
        repository.getDetailLine(idLocalCompany, idBusline, state).flowOn(dispatcher)
}