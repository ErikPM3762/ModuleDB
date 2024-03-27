package com.example.moduledb.controlDB.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moduledb.controlDB.data.remote.response.lines.Feature
import com.example.moduledb.controlDB.data.remote.response.lines.Geometry
import com.example.moduledb.controlDB.data.remote.response.lines.Properties
import com.example.moduledb.controlDB.data.remote.response.lines.Style
import com.example.moduledb.controlDB.utils.Converters
import com.google.gson.annotations.SerializedName

@Entity
data class StyleEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id", defaultValue = "0") val id: Long = 0,
    val strokeWidth: Int,
    val fill: String,
    val fillOpacity : Any
)