package com.example.moduledb.controlDB.data.remote.response.versionTablePointRecharge


import com.example.moduledb.controlDB.data.models.MDbVTPointInterestResponse
import com.example.moduledb.controlDB.data.models.MDbVTPointRechargeResponse
import com.google.gson.annotations.SerializedName

data class VTPointRechargeResultResponse (
    @SerializedName("updateVersion")
    val updateVersion: Long = 0L,
){
    fun parse(): MDbVTPointRechargeResponse = MDbVTPointRechargeResponse(
        updateVersion = updateVersion
    )
}

