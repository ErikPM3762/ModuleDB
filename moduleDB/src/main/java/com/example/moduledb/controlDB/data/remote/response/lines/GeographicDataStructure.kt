package com.example.moduledb.controlDB.data.remote.response.lines

import com.google.gson.annotations.SerializedName

data class GeographicDataStructure(
    @SerializedName("initial_map_coordinates")
    val initialMapCoordinates: List<Double>
)
