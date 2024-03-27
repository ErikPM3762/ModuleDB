package com.example.moduledb.controlDB.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class MDbMacroRegions(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "idMacroRegion") val idMacroRegion: String,
    @ColumnInfo(name = "desMacroRegion") val desMacroRegion: String,
    @ColumnInfo(name = "latitudeMacroRegion") val latitudeMacroRegion: String,
    @ColumnInfo(name = "longitudeMacroRegion") val longitudeMacroRegion: String,
    @ColumnInfo(name = "routeCount") val routeCount: Int
)
