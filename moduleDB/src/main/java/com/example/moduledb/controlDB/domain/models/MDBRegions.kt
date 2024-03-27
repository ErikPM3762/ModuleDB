package com.example.moduledb.controlDB.domain.models


data class MDBRegions(
    val idRegion: String = "",
    val desRegion: String = "",
    val latitudeRegion: String = "",
    val longitudeRegion: String = "",
    val idMacroRegion: String = "",
    val desMacroRegion: String,
    val longitudeMacroRegion: String = "",
    val latitudeMacroRegion: String = ""
)
