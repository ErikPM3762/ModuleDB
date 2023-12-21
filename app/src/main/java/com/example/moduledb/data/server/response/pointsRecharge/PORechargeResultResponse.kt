package com.example.moduledb.data.server.response.pointsRecharge

import com.example.moduledb.controlDB.data.models.MDbPOIsResponse
import com.example.moduledb.controlDB.data.models.MDbPORechargeResponse
import com.google.gson.annotations.SerializedName

data class PORechargeResultResponse (
    @SerializedName("updateVersion")
    val updateVersion: String = "",
    @SerializedName("listaCentroRecarga")
    val pointOfRechargeList: ArrayList<MDbPORechargeResponse>?
)
