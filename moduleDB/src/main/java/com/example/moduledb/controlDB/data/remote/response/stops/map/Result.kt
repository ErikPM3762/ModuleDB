package com.example.moduledb.controlDB.data.remote.response.stops.map

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("features")
    val features: List<Feature>,
    @SerializedName("type")
    val type: String
)