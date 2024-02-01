package com.example.moduledb.controlDB.usecase

import com.example.moduledb.controlDB.data.local.entities.MDbListStops
import com.example.moduledb.controlDB.data.remote.models.MDBStops
import com.example.moduledb.controlDB.data.remote.repository.StopsRepository
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStops @Inject constructor(private val regionsRepository: StopsRepository) {

    suspend fun invoke(idLocalCompany : Int) : Flow<NetResult<List<Any>>> =
        regionsRepository.getStopsOracle(idLocalCompany)
}