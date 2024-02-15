package com.example.moduledb.controlDB.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MDbRouteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", defaultValue = "0")
    val id: Long = 0,
    @ColumnInfo(name = "idBusLine")
    val idBusLine: String,
    @ColumnInfo(name = "pathIdBusLine")
    val pathIdBusLine: String,
    @ColumnInfo(name = "pathIdDescription")
    val pathIdDescription: String,
    @ColumnInfo(name = "direction")
    val direction: String
)