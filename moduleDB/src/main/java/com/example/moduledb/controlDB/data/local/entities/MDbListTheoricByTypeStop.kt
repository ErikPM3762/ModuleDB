package com.example.moduledb.controlDB.data.local.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moduledb.controlDB.data.remote.response.teroicByStop.Day
import com.example.moduledb.controlDB.utils.Converters
import com.google.gson.annotations.SerializedName


@Entity
data class MDbListTheoricByTypeStop(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id", defaultValue = "0") val id: Long = 0,
    @TypeConverters(Converters::class)
    val busStop: List<BusStopEntity>,
    val idLineGenerate: String,
    val tripCode: String
)