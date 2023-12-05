package com.example.moduledb.controlDB.data.models

import com.google.gson.annotations.SerializedName



data class PointsInterestResponse(
    @SerializedName("idPointOfInterest") val id: String,
    @SerializedName("PointOfInterest") val name: String,
    @SerializedName("descPointOfInterest") val description: String,
    @SerializedName("pointOfInterestAddress") val address: String,
    @SerializedName("pointOfInterestPhone") val phone: String?,
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude: String
)
