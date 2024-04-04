package com.example.moduledb.controlDB.domain.usecase

import com.example.moduledb.controlDB.data.local.entities.MDbPOIs
import com.example.moduledb.controlDB.data.remote.repository.InfoMapRepository
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPointsInterest @Inject constructor(private val infoMapRepository: InfoMapRepository) {

    suspend operator fun invoke(idLocalCompany: Int): Flow<NetResult<List<MDbPOIs>>> =
        infoMapRepository.fetchPointInterestData(idLocalCompany)
}