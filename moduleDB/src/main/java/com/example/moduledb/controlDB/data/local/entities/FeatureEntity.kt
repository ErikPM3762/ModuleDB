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

@Entity
data class FeatureEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id", defaultValue = "0") val id: Long = 0,
    @TypeConverters(Converters::class)
    val geometry: GeometryEntity,
    @TypeConverters(Converters::class)
    val properties: PropertiesEntity,
    val type: String,
    @TypeConverters(Converters::class)
    val style: StyleEntity?,
)