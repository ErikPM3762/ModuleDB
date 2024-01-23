package com.example.moduledb.controlDB.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moduledb.controlDB.data.remote.response.lines.Region

@Entity
data class RegionEntity(
    @PrimaryKey val idRegion: String,
    val desRegion: String,
    val parentId: String
)