package com.example.moduledb.controlDB.data.remote.request

import com.google.gson.annotations.SerializedName

data class LinesListRequest(
    @SerializedName("idFront")
    val idFront: Int,
    @SerializedName("country")
    val country: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("cityOrTown")
    val cityOrTown: String,
    @SerializedName("idLocalCompany")
    val idLocalCompany: String
)
