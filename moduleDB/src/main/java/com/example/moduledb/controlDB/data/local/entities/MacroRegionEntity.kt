package com.example.moduledb.controlDB.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moduledb.controlDB.data.remote.response.macroRegions.MacroRegion

@Entity
data class MacroRegionEntity(
    @PrimaryKey val idMacroRegion: String,
    val desMacroRegion: String,
    val parentId: String
)
