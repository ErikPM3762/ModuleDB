package com.example.moduledb.controlDB.data.remote.response.stops

import com.example.moduledb.controlDB.data.remote.models.MDBStops
import com.google.gson.annotations.SerializedName

data class StopsResultResponse (
    @SerializedName("busStop")
    val stopsList: List<MDBStops>?
        )