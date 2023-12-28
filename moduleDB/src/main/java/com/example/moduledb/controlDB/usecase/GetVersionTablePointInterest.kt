package com.example.moduledb.controlDB.usecase

import com.example.moduledb.controlDB.data.models.MDbPORechargeResponse
import com.example.moduledb.controlDB.data.models.MDbVTPointInterestResponse
import com.example.moduledb.controlDB.data.remote.repository.InfoMapRepository
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVersionTablePointInterest @Inject constructor(private val infoMapRepository: InfoMapRepository){

    suspend fun invoke() : Flow<NetResult<MDbVTPointInterestResponse>> =
        infoMapRepository.getVersionTablePointInterest()
}