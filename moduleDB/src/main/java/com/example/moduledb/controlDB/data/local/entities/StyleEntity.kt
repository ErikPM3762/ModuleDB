package com.example.moduledb.controlDB.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StyleEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id", defaultValue = "0") val id: Long = 0,
    val strokeWidth: Int,
    val fill: String,
    val fillOpacity : Any
)