package com.example.moduledb.controlDB.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class PointsInterest(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "idPointOfInterest") val idPointOfInterest: String,
    @ColumnInfo(name = "PointOfInterest") val PointOfInterest: String,
    @ColumnInfo(name = "descPointOfInterest") val descPointOfInterest: String,
    @ColumnInfo(name = "pointOfInterestAddress") val pointOfInterestAddress: String,
    @ColumnInfo(name = "pointOfInterestPhone") val pointOfInterestPhone: String,
    @ColumnInfo(name = "latitude") val latitude: String,
    @ColumnInfo(name = "longitude") val longitude: String
)
