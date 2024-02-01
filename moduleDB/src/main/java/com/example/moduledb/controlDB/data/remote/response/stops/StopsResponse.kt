package com.example.moduledb.controlDB.data.remote.response.stops

import com.example.moduledb.controlDB.data.remote.response.HeaderResponse
import com.google.gson.annotations.SerializedName

data class StopsResponse(
    @SerializedName("header")
    val header: HeaderResponse,
    @SerializedName("result")
    val result: StopsResultResponse?
)
