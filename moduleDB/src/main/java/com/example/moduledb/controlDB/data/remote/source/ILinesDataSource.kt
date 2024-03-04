package com.example.moduledb.controlDB.data.remote.source

import com.example.moduledb.controlDB.data.local.entities.MDbLinesDetail
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.flow.Flow

interface ILinesDataSource {

    suspend fun getDetailLineById(
        idLocalCompany: Int,
        idBusline: String,
        state: String
    ): Flow<NetResult<List<MDbLinesDetail>>>

    fun getRouteDetailByIds(
        idLocalCompany: Int,
        idBusline: String,
        idPath: String
    ): Flow<NetResult<List<MDbLinesDetail>>>

}