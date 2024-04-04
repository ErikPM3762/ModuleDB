package com.example.moduledb.controlDB.data.remote.response.pointsInterest

import com.google.gson.annotations.SerializedName

data class GetPOIsAwsResult(
    @SerializedName("pointOfInterestList")
    val pointsOfInterest: List<PointOfInterestAws>,
    @SerializedName("updateVersion")
    val updateVersion: String
)