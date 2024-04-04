package com.example.moduledb.controlDB.data.remote.response.stops.map

import com.google.gson.annotations.SerializedName

data class Feature(
    @SerializedName("geometry")
    val geometry: Geometry,
    @SerializedName("properties")
    val properties: Properties,
    @SerializedName("type")
    val type: String
)