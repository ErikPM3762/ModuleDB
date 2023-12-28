package com.example.moduledb.data.server.request

import com.google.gson.annotations.SerializedName

data class RechargingPointsRequest(
    @SerializedName("idFront")
    val idFront: Int = 51,
    @SerializedName("country")
    val country: String = "mexico",
    @SerializedName("state")
    val state: String = "",
    @SerializedName("cityOrTown")
    val cityOrTown: String = "",
    @SerializedName("idLocalCompany")
    val idLocalCompany: String = "11"
)
