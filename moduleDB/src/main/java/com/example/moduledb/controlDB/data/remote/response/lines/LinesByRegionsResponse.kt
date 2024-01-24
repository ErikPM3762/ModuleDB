package com.example.moduledb.controlDB.data.remote.response.lines

import com.example.moduledb.controlDB.data.remote.response.HeaderResponse
import com.google.gson.annotations.SerializedName

data class LinesByRegionsResponse(
    @SerializedName("header") val header: HeaderResponse,
    @SerializedName("result") val result: LinesByRegionResultResponse?
)
