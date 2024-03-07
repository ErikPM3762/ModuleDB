package com.example.moduledb.controlDB.data.remote.source

import com.example.moduledb.controlDB.data.local.entities.MDbListTheoricByTypeStop
import com.example.moduledb.controlDB.data.remote.response.teroicByStop.BusStopResponse
import com.example.moduledb.controlDB.data.remote.response.teroicByStop.TimeTableDaybusStop
import com.example.moduledb.controlDB.domain.models.MDBStops
import com.example.moduledb.controlDB.domain.models.MDBTheoricByTypeStop
import com.example.moduledb.controlDB.domain.models.MDbPOIsResponse
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.flow.Flow

interface IStopsDataSource {

    suspend fun getTeoricByTypeStop(idLocalCompany: Int, idBusLine: String, tripCode: String): Flow<NetResult<MDBTheoricByTypeStop>>

    suspend fun getStops(idLocalCompany: Int): Flow<NetResult<List<MDBStops>>>

}