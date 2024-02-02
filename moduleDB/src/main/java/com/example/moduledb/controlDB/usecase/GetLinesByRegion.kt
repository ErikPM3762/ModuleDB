package com.example.moduledb.controlDB.usecase

import com.example.moduledb.controlDB.data.local.entities.MDbLinesByRegion
import com.example.moduledb.controlDB.data.remote.repository.RegionsRepository
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLinesByRegion @Inject constructor(private val regionsRepository: RegionsRepository) {

    suspend fun invoke(idLocalCompany : Int, idMacroRegion: String) : Flow<NetResult<List<Any>>> =
        regionsRepository.getLinesByRegion(idLocalCompany, idMacroRegion)
}