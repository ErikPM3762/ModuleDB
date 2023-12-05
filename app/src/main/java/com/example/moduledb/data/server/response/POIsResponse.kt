package com.example.moduledb.data.server.response

import com.google.gson.annotations.SerializedName

data class POIsResponse (
    @SerializedName("header")
    val header: HeaderResponse,
    @SerializedName("result")
    val result: POIsResultResponse?
)