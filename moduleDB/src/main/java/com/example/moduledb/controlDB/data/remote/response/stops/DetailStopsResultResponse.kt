package com.example.moduledb.controlDB.data.remote.response.stops

import com.example.moduledb.controlDB.domain.models.MDBDetailStop
import com.example.moduledb.controlDB.domain.models.MDBStops
import com.google.gson.annotations.SerializedName

data class DetailStopsResultResponse (
    @SerializedName("busStop")
    val stopsList: MDBDetailStop?
        )