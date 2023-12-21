package com.example.moduledb.data.server.response.pointsInterest

import com.example.moduledb.controlDB.data.models.MDbPOIsResponse
import com.google.gson.annotations.SerializedName

data class POIsResultResponse (
    @SerializedName("updateVersion")
    val updateVersion: String = "",
    @SerializedName("pointOfInterestList")
    val pointOfInterestList: ArrayList<MDbPOIsResponse>?
)
