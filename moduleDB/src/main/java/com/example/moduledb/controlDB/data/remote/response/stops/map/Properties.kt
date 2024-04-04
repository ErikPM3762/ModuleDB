package com.example.moduledb.controlDB.data.remote.response.stops.map

import com.google.gson.annotations.SerializedName

data class Properties(
    @SerializedName("idBusStop")
    val idBusStop: String,
    @SerializedName("popupContent")
    val popupContent: String
)