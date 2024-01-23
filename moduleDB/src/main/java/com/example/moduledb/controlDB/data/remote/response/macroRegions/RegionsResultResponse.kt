package com.example.moduledb.controlDB.data.remote.response.macroRegions

import com.example.moduledb.controlDB.data.models.MDBMacroRegions
import com.google.gson.annotations.SerializedName

data class RegionsResultResponse(
    val updateVersion: String = "",
    @SerializedName("macroRegionList")
    val statesList: List<MDBMacroRegions>?
)