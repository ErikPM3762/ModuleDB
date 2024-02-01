package com.example.moduledb.controlDB.data.remote.response.macroRegions

import com.example.moduledb.controlDB.data.remote.models.MDBMacroRegions
import com.google.gson.annotations.SerializedName

data class MacroRegionsResultResponse(
    val updateVersion: String = "",
    @SerializedName("macroRegionList")
    val statesList: List<MDBMacroRegions>?
)
