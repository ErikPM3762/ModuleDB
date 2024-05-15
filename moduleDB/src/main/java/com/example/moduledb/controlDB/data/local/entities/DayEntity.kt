package com.example.moduledb.controlDB.data.local.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moduledb.controlDB.utils.Converters


@Entity
data class DayEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id", defaultValue = "0") val id: Long = 0,
    val dayType: String,
    @TypeConverters(Converters::class)
    val schedules: List<ScheduleEntity>
)