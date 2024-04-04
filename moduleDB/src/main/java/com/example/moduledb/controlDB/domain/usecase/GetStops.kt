package com.example.moduledb.controlDB.domain.usecase

import com.example.moduledb.controlDB.data.local.entities.MDbListStops
import com.example.moduledb.controlDB.data.remote.repository.StopsRepositoryImpl
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetStops @Inject constructor(private val regionsRepository: StopsRepositoryImpl) {

    suspend operator fun invoke(
        idLocalCompany: Int,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<NetResult<List<MDbListStops>>> =
        regionsRepository.getStops(idLocalCompany).flowOn(dispatcher)
}