package com.example.moduledb.controlDB.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moduledb.controlDB.utils.Converters

@Entity
data class PropertiesEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id", defaultValue = "0") val id: Long = 0,
    val busLineCrossing: Any?,
    val color: String? = "",
    val desBusStop: String? = "",
    val idBusLine: String,
    val idBusSAE: String? = "",
    val idBusStop: String,
    val node: String?,
    @TypeConverters(Converters::class)
    val brands: List<BrandsEntity>? = emptyList(),
)