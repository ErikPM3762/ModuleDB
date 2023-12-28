package com.example.moduledb.controlDB.data.remote.response.versionTablePointInterest

import com.example.moduledb.controlDB.data.remote.response.HeaderResponse
import com.google.gson.annotations.SerializedName

data class VTPointInterestResponse (
    @SerializedName("header")
    val header: HeaderResponse,
    @SerializedName("result")
    val result: VTPointInterestResultResponse?
)