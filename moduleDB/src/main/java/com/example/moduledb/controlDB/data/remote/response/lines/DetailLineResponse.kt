package com.example.moduledb.controlDB.data.remote.response.lines

import android.graphics.Region
import com.example.moduledb.controlDB.data.remote.response.HeaderResponse
import com.example.moduledb.controlDB.data.remote.response.macroRegions.MacroRegion
import com.google.gson.annotations.SerializedName

data class DetailLineResponse(
    @SerializedName("header") val header: HeaderResponse,
    @SerializedName("result") val result: DetailLineResultResponse?
)
