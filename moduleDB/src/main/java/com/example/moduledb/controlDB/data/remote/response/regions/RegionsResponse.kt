package com.example.moduledb.controlDB.data.remote.response.regions

import com.example.moduledb.controlDB.data.remote.response.HeaderResponse
import com.google.gson.annotations.SerializedName

data class RegionsResponse(
    @SerializedName("header")
    val header: HeaderResponse,
    @SerializedName("result")
    val result: RegionsResultResponse?
)
