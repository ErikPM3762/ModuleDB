package com.example.moduledb.data.server.response.pointsRecharge

import com.example.moduledb.data.server.response.HeaderResponse
import com.google.gson.annotations.SerializedName

data class PORechargeResponse (
    @SerializedName("header")
    val header: HeaderResponse,
    @SerializedName("result")
    val result: PORechargeResultResponse?
)