package com.example.moduledb.controlDB.usecase

import com.example.moduledb.controlDB.data.remote.models.MDbVTPointRechargeResponse
import com.example.moduledb.controlDB.data.remote.repository.InfoMapRepository
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVersionTablePointRecharge @Inject constructor(private val infoMapRepository: InfoMapRepository){

    suspend fun invoke() : Flow<NetResult<MDbVTPointRechargeResponse>> =
        infoMapRepository.getVersionTablePointRecharge()
}