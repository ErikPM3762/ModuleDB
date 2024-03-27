package com.example.moduledb.controlDB.data.remote.response.macroRegions

import com.google.gson.annotations.SerializedName

data class MacroRegion(
    @SerializedName("idMacroRegion") val idMacroRegion: String,
    @SerializedName("desMacroRegion") val desMacroRegion: String,
    @SerializedName("latitudeMacroRegion") val latitudeMacroRegion: String,
    @SerializedName("longitudeMacroRegion") val longitudeMacroRegion: String
)
