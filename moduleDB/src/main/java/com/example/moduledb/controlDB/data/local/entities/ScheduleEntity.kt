package com.example.moduledb.controlDB.data.local.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moduledb.controlDB.data.remote.response.teroicByStop.Schedule
import com.example.moduledb.controlDB.utils.Converters
import com.google.gson.annotations.SerializedName


@Entity
data class ScheduleEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id", defaultValue = "0") val id: Long = 0,
    val dayTime: String,
    val pathIdBusLine: String,
    val arrivalBusStop: String,
    val idBusStop: String
)