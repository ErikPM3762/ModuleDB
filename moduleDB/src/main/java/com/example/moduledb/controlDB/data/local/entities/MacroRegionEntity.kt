package com.example.moduledb.controlDB.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MacroRegionEntity(
    @PrimaryKey val idMacroRegion: String,
    val desMacroRegion: String,
    val parentId: String
)
