package com.example.moduledb.controlDB.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class MDbPOIs(
    @PrimaryKey val id: Long = 0,
    @ColumnInfo(name = "idPointOfInterest") val idPointOfInterest: String,
    @ColumnInfo(name = "PointOfInterest") val pointOfInterest: String,
    @ColumnInfo(name = "descPointOfInterest") val descPointOfInterest: String,
    @ColumnInfo(name = "pointOfInterestAddress") val pointOfInterestAddress: String,
    @ColumnInfo(name = "pointOfInterestPhone") val pointOfInterestPhone: String,
    @ColumnInfo(name = "latitude") val latitude: String,
    @ColumnInfo(name = "longitude") val longitude: String
)
