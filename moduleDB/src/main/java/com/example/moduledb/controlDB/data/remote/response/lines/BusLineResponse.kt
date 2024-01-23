package com.example.moduledb.controlDB.data.remote.response.lines

import com.example.moduledb.controlDB.data.remote.response.macroRegions.MacroRegion
import com.google.gson.annotations.SerializedName

data class BusLineResponse(
    @SerializedName("idBusLine") val idBusLine: String,
    @SerializedName("idBusSAE") val idBusSAE: String,
    @SerializedName("descBusLine") val descBusLine: String,
    @SerializedName("desLocalCompany") val desLocalCompany: String,
    @SerializedName("color") val color: String? = null,
    @SerializedName("brands") val brands: List<Brand>?,
    @SerializedName("macroRegions") val macroRegions: List<MacroRegion>?,
    @SerializedName("regions") val regions: List<Region>?
)
