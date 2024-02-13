package com.example.moduledb.controlDB.usecase

import com.example.moduledb.controlDB.data.local.entities.MDbLinesDetail
import com.example.moduledb.controlDB.data.remote.repository.LinesRepository
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDetailLine @Inject constructor(private val repository: LinesRepository) {

    suspend fun invoke(idLocalCompany: Int, idBusline: String, state: String) : Flow<NetResult<List<MDbLinesDetail>>> =
        repository.getDetailLine(idLocalCompany, idBusline, state )
}