package com.example.moduledb.controlDB.data.remote.response.lines

import com.google.gson.annotations.SerializedName

data class Style(
    @SerializedName("stroke-width")
    val strokeWidth: Int,
    val fill: String,
    @SerializedName("fill-opacity")
    val fillOpacity : Any
)
