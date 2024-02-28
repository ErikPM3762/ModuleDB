package com.example.moduledb.controlDB.data.remote.source

import com.example.moduledb.controlDB.domain.models.MDBStops
import com.example.moduledb.controlDB.domain.models.MDbPOIsResponse
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.flow.Flow

interface IStopsDataSource {

    suspend fun getTeoricByTypeStop(idLocalCompany: Int, idBusLine: String, tripCode: String): Flow<NetResult<List<MDbPOIsResponse>>>

    suspend fun getStops(idLocalCompany: Int): Flow<NetResult<List<MDBStops>>>

}