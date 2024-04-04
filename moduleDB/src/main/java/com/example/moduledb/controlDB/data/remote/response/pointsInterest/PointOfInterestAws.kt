package com.example.moduledb.controlDB.data.remote.response.pointsInterest

import com.google.gson.annotations.SerializedName

data class PointOfInterestAws(
    @SerializedName("PointOfInterest")
    val head: String,
    @SerializedName("descPointOfInterest")
    val description: String,
    @SerializedName("idPointOfInterest")
    val id: Int,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("pointOfInterestAddress")
    val address: String,
    @SerializedName("pointOfInterestPhone")
    val phone: String
)