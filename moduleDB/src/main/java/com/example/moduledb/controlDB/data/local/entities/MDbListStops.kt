package com.example.moduledb.controlDB.data.local.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moduledb.controlDB.utils.Converters
import com.google.gson.annotations.SerializedName


@Entity
data class MDbListStops(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id", defaultValue = "0") val id: Long = 0,
    val idBusStop: Int,
    val desBusStop: String?,
    @TypeConverters(Converters::class)
    val coordinates: List<String>?,
    @TypeConverters(Converters::class)
    val buslineCrossing: List<String>?,
    @TypeConverters(Converters::class)
    val brands: List<BusStopBrandsEntity>?
)