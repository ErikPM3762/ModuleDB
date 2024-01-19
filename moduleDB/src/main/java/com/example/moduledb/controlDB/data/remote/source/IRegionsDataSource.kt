package com.example.moduledb.controlDB.data.remote.source

import com.example.moduledb.controlDB.data.models.MDBMacroRegions
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.flow.Flow

interface IRegionsDataSource {
    suspend fun getStateInfo(idLocalCompany: Int): Flow<NetResult<List<MDBMacroRegions>>>
}