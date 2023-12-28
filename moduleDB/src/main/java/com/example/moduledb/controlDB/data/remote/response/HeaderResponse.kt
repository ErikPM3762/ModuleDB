package com.example.moduledb.controlDB.data.remote.response

import com.google.gson.annotations.SerializedName

data class HeaderResponse (
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)

