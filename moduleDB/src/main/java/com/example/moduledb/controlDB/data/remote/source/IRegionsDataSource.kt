package com.example.moduledb.controlDB.data.remote.source

import com.example.moduledb.controlDB.data.local.entities.MDbLinesByRegion
import com.example.moduledb.controlDB.data.local.entities.MDbListLines
import com.example.moduledb.controlDB.data.local.entities.MDdRegions
import com.example.moduledb.controlDB.data.remote.models.MDBMacroRegions
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body

interface IRegionsDataSource {
    suspend fun getStateInfo(idLocalCompany: Int): Flow<NetResult<List<MDBMacroRegions>>>
    suspend fun getRegionsInfo(idLocalCompany: Int): Flow<NetResult<List<MDdRegions>>>
    suspend fun getLinesByMacroRegions(
        idLocalCompany: Int,
        idMacroRegion: String
    ): Flow<NetResult<List<MDbListLines>>>

    suspend fun getLinesByRegions(
        idLocalCompany: Int,
        idMacroRegion: String
    ): Flow<NetResult<List<MDbLinesByRegion>>>

    fun getRoutesByIdLine(idLocalCompany: String, idLines: String): Flow<NetResult<Body>>
}