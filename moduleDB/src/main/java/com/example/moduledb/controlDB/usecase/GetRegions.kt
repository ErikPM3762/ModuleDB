package com.example.moduledb.controlDB.usecase

import com.example.moduledb.controlDB.data.remote.models.MDBRegions
import com.example.moduledb.controlDB.data.remote.repository.RegionsRepository
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRegions @Inject constructor(private val regionsRepository: RegionsRepository) {

    suspend fun invoke(idLocalCompany : Int) : Flow<NetResult<List<MDBRegions>>> =
        regionsRepository.getRegions(idLocalCompany)
}