package com.example.moduledb.controlDB.domain.usecase

import com.example.moduledb.controlDB.data.local.entities.MDbPORecharge
import com.example.moduledb.controlDB.domain.models.MDbPORechargeResponse
import com.example.moduledb.controlDB.data.remote.repository.InfoMapRepository
import com.example.moduledb.controlDB.data.remote.response.versionTablePointInterest.VTPointInterestResponse
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPointsRecharge @Inject constructor(private val infoMapRepository: InfoMapRepository){

    suspend operator fun invoke(idLocalCompany: Int) : Flow<NetResult<List<MDbPORecharge>>> =
        infoMapRepository.fetchPointOfRechargeData(idLocalCompany)
}