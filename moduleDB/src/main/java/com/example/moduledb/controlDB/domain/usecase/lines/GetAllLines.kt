package com.example.moduledb.controlDB.domain.usecase.lines

import com.example.moduledb.controlDB.data.remote.repository.RegionsRepository
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllLines @Inject constructor(private val regionsRepository: RegionsRepository) {
    operator fun invoke(
        idLocalCompany: Int,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<NetResult<List<Any>>> = regionsRepository.getAllLines(idLocalCompany).flowOn(dispatcher)
}
