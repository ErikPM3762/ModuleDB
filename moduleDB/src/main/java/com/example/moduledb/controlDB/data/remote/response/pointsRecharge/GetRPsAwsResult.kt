package com.example.moduledb.controlDB.data.remote.response.pointsRecharge

import com.google.gson.annotations.SerializedName

data class GetRPsAwsResult(
    @SerializedName("listaCentroRecarga")
    val rpCenters: List<RechargingCenter>,
    @SerializedName("updateVersion")
    val updateVersion: String
)