package com.example.moduledb.controlDB.domain.repository

import com.example.moduledb.controlDB.data.DataResult
import com.example.moduledb.controlDB.domain.models.MDBDetailStop

interface StopsRepository {
    suspend fun getDetailStopsById(idLocalCompany: Int, idBusStop:String) : DataResult<MDBDetailStop, Exception>
}