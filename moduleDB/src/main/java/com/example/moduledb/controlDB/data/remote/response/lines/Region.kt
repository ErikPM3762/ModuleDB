package com.example.moduledb.controlDB.data.remote.response.lines

import com.google.gson.annotations.SerializedName

data class Region (
    @SerializedName("idRegion") val idRegion: String?,
    @SerializedName("desRegion") val desRegion: String?
)