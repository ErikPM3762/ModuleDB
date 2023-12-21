package com.example.moduledb.data.server.response.pointsInterest

import com.example.moduledb.data.server.response.HeaderResponse
import com.google.gson.annotations.SerializedName

data class POIsResponse (
    @SerializedName("header")
    val header: HeaderResponse,
    @SerializedName("result")
    val result: POIsResultResponse?
)