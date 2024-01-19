package com.example.moduledb.controlDB.data.remote.response.macroRegions

import com.example.moduledb.controlDB.data.remote.response.HeaderResponse
import com.google.gson.annotations.SerializedName

data class MacroRegionsResponse(
    @SerializedName("header")
    val header: HeaderResponse,
    @SerializedName("result")
    val result: RegionsResultResponse?
)
