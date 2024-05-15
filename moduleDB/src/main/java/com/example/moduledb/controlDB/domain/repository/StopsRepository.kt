package com.example.moduledb.controlDB.domain.repository

import com.example.moduledb.controlDB.domain.models.MDBDetailStop
import com.example.services.data.DataResult

interface StopsRepository {
    suspend fun getDetailStopsById(idLocalCompany: Int, idBusStop:String) : DataResult<MDBDetailStop, Exception>
}