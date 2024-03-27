package com.example.moduledb.controlDB.data.remote.response.lines

import com.example.moduledb.controlDB.data.remote.response.HeaderResponse
import com.google.gson.annotations.SerializedName

data class LinesByMacroRegionsResponse(
    @SerializedName("header") val header: HeaderResponse,
    @SerializedName("result") val result: LinesByMacroRegionResultResponse?
)
