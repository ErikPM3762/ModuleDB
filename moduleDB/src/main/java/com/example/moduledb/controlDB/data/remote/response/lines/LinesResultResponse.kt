package com.example.moduledb.controlDB.data.remote.response.lines

import com.example.moduledb.controlDB.data.local.entities.MDbListLines
import com.google.gson.annotations.SerializedName

data class LinesResultResponse(
    val updateVersion: String = "",
    @SerializedName("busLine") val busLine: List<MDbListLines>?
)
