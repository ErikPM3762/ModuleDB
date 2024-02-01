package com.example.moduledb.controlDB.data.remote.response.teroicByStop

import com.example.moduledb.controlDB.data.remote.response.HeaderResponse
import com.google.gson.annotations.SerializedName

data class TeoricsByTypeStopResponse(
    @SerializedName("header")
    val header: HeaderResponse,
    @SerializedName("result")
    val result: TimeTableResponse?
)