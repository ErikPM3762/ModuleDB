package com.example.moduledb.controlDB.data.remote.source

import com.example.moduledb.controlDB.data.local.entities.MDbListStops
import com.example.moduledb.controlDB.domain.models.MDBStops
import com.example.moduledb.controlDB.domain.models.MDBTheoricByTypeStop
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.flow.Flow

interface IStopsDataSource {

    suspend fun getTeoricByTypeStop(
        idLocalCompany: Int,
        idBusLine: String,
        tripCode: String
    ): Flow<NetResult<MDBTheoricByTypeStop>>

    suspend fun getStops(idLocalCompany: Int): Flow<NetResult<List<MDbListStops>>>

    fun getMapStops(idLocalCompany: Int): Flow<NetResult<List<MDbListStops>>>

}