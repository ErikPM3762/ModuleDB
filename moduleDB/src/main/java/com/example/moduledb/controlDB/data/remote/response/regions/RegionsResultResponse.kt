package com.example.moduledb.controlDB.data.remote.response.regions

import com.example.moduledb.controlDB.data.local.entities.MDdRegions
import com.example.moduledb.controlDB.domain.models.MDBRegions
import com.google.gson.annotations.SerializedName

data class RegionsResultResponse(
    val updateVersion: String = "",
    @SerializedName("regionList")
    val statesList: List<MDdRegions>?
)
