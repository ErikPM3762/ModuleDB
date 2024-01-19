package com.example.moduledb.controlDB.data.remote.response.lines

data class Feature(
    val geometry: Geometry,
    val properties: Properties,
    val type: String
)
