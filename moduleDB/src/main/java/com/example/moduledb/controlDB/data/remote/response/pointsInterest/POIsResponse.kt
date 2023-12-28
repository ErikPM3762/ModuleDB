package com.example.moduledb.controlDB.data.remote.response.pointsInterest

import com.example.moduledb.controlDB.data.remote.response.HeaderResponse
import com.google.gson.annotations.SerializedName

data class POIsResponse (
    @SerializedName("header")
    val header: HeaderResponse,
    @SerializedName("result")
    val result: POIsResultResponse?
)