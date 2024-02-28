package com.example.moduledb.controlDB.data.remote.source

import com.example.moduledb.controlDB.data.local.entities.MDbLinesDetail
import com.example.moduledb.controlDB.domain.models.MDBStops
import com.example.moduledb.controlDB.domain.models.MDbPOIsResponse
import com.example.moduledb.controlDB.data.remote.response.lines.BusLine
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.flow.Flow

interface ILinesDataSource {

    suspend fun getDetailLineById(idLocalCompany: Int, idBusline: String, state: String): Flow<NetResult<List<MDbLinesDetail>>>

}