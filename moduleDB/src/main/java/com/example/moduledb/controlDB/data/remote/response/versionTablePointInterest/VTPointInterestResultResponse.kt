package com.example.moduledb.controlDB.data.remote.response.versionTablePointInterest


import com.example.moduledb.controlDB.data.remote.models.MDbVTPointInterestResponse
import com.google.gson.annotations.SerializedName

data class VTPointInterestResultResponse (
    @SerializedName("updateVersion")
    val updateVersion: Long = 0L,
){
    fun parse(): MDbVTPointInterestResponse = MDbVTPointInterestResponse(
        updateVersion = updateVersion
    )
}

