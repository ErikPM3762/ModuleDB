package com.example.moduledb.controlDB.data.remote.response.stops.map

import com.example.moduledb.controlDB.data.remote.response.HeaderResponse
import com.google.gson.annotations.SerializedName

data class GetMapStopsAwsResponse(
    @SerializedName("header")
    val header: HeaderResponse,
    @SerializedName("result")
    val result: Result
)