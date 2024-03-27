package com.example.moduledb.controlDB.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MDdRegions(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "idRegion") val idRegion: String,
    @ColumnInfo(name = "desRegion") val desRegion: String,
    @ColumnInfo(name = "latitudeRegion") val latitudeRegion: String,
    @ColumnInfo(name = "longitudeRegion") val longitudeRegion: String,
    @ColumnInfo(name = "idMacroRegion") val idMacroRegion: String,
    @ColumnInfo(name = "desMacroRegion") val desMacroRegion: String,
    @ColumnInfo(name = "latitudeMacroRegion") val latitudeMacroRegion: String,
    @ColumnInfo(name = "longitudeMacroRegion") val longitudeMacroRegion: String,
)
