package com.example.moduledb.controlDB.data.remote.response.pointsRecharge

import com.example.moduledb.controlDB.data.remote.response.HeaderResponse
import com.google.gson.annotations.SerializedName

data class PORechargeResponse (
    @SerializedName("header")
    val header: HeaderResponse,
    @SerializedName("result")
    val result: PORechargeResultResponse?
)