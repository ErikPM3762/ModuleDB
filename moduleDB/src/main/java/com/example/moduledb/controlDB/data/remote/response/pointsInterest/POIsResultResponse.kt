package com.example.moduledb.controlDB.data.remote.response.pointsInterest

import com.example.moduledb.controlDB.data.remote.models.MDbPOIsResponse
import com.google.gson.annotations.SerializedName

data class POIsResultResponse (
    @SerializedName("updateVersion")
    val updateVersion: String = "",
    @SerializedName("pointOfInterestList")
    val pointOfInterestList: ArrayList<MDbPOIsResponse>?
)
