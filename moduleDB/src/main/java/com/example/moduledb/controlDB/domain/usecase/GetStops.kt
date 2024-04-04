package com.example.moduledb.controlDB.domain.usecase

import com.example.moduledb.controlDB.data.remote.repository.StopsRepositoryImpl
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStops @Inject constructor(private val regionsRepository: StopsRepositoryImpl) {

    suspend fun invoke(idLocalCompany : Int) : Flow<NetResult<List<Any>>> =
        regionsRepository.getStops(idLocalCompany)
}