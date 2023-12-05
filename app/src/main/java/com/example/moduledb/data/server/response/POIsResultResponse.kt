package com.example.moduledb.data.server.response

import com.example.moduledb.controlDB.data.models.PointsInterestResponse
import com.google.gson.annotations.SerializedName

data class POIsResultResponse (
    @SerializedName("updateVersion")
    val updateVersion: String = "",
    @SerializedName("pointOfInterestList")
    val pointOfInterestList: ArrayList<PointsInterestResponse>?
)
