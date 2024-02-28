package com.example.moduledb.controlDB.data.remote.response.stops

import com.example.moduledb.controlDB.data.remote.response.HeaderResponse
import com.example.moduledb.controlDB.data.remote.response.lines.DetailLineResultResponse
import com.google.gson.annotations.SerializedName

data class DetailStopsResponse (
    @SerializedName("header") val header: HeaderResponse,
    @SerializedName("result") val result: DetailStopsResultResponse?
        )