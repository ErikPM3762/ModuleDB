package com.example.moduledb.controlDB.data.remote.response.versionTablePointRecharge

import com.example.moduledb.controlDB.data.remote.response.HeaderResponse
import com.google.gson.annotations.SerializedName

data class VTPointRechargeResponse (
    @SerializedName("header")
    val header: HeaderResponse,
    @SerializedName("result")
    val result: VTPointRechargeResultResponse?
)