package com.example.moduledb.controlDB.data.remote.response.pointsInterest

import com.example.moduledb.controlDB.data.remote.response.HeaderResponse
import com.google.gson.annotations.SerializedName

data class GetPOIsAwsResponse(
    @SerializedName("header")
    val header: HeaderResponse,
    val result: GetPOIsAwsResult
)