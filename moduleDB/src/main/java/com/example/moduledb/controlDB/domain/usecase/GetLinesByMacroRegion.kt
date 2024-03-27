package com.example.moduledb.controlDB.domain.usecase

import com.example.moduledb.controlDB.data.local.entities.MDbListLines
import com.example.moduledb.controlDB.data.remote.repository.RegionsRepository
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLinesByMacroRegion @Inject constructor(private val regionsRepository: RegionsRepository) {

    suspend fun invoke(idLocalCompany : Int, idMacroRegion: String) : Flow<NetResult<List<Any>>> =
        regionsRepository.getLinesByMacroRegion(idLocalCompany, idMacroRegion)
}