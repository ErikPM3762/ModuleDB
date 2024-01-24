package com.example.moduledb.controlDB.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RegionEntity(
    @PrimaryKey val idRegion: String,
    val desRegion: String,
    val parentId: String
)