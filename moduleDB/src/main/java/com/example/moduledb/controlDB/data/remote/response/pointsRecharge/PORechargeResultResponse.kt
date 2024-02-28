package com.example.moduledb.controlDB.data.remote.response.pointsRecharge

import com.example.moduledb.controlDB.domain.models.MDbPOIsResponse
import com.example.moduledb.controlDB.domain.models.MDbPORechargeResponse
import com.google.gson.annotations.SerializedName

data class PORechargeResultResponse (
    @SerializedName("updateVersion")
    val updateVersion: String = "",
    @SerializedName("listaCentroRecarga")
    val pointOfRechargeList: ArrayList<MDbPORechargeResponse>?
)
