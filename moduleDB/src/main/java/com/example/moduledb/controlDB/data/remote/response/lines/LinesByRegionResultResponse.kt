package com.example.moduledb.controlDB.data.remote.response.lines

import com.example.moduledb.controlDB.data.local.entities.MDbLinesByRegion
import com.google.gson.annotations.SerializedName

data class LinesByRegionResultResponse(
    val updateVersion: String = "",
    @SerializedName("busLine") val busLine: List<MDbLinesByRegion>?
)
