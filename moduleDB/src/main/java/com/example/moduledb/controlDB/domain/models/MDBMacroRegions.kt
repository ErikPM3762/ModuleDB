package com.example.moduledb.controlDB.domain.models


data class MDBMacroRegions(
    val idMacroRegion: String = "",
    val desMacroRegion: String = "",
    val latitudeMacroRegion: String? = "",
    val longitudeMacroRegion: String? = "",
    val routeCount: Int = 0
)
