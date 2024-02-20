package com.example.moduledb.controlDB.usecase

import com.example.moduledb.controlDB.data.local.entities.MDbLinesDetail
import com.example.moduledb.controlDB.data.remote.repository.LinesRepository
import com.example.moduledb.controlDB.utils.NetResult
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
    ): Flow<NetResult<List<MDbLinesDetail>>> =
        repository.getDetailLine(idLocalCompany, idBusline, state).flowOn(dispatcher)
}