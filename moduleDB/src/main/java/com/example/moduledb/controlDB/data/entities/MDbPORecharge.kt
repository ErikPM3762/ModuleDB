package com.example.moduledb.controlDB.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MDbPORecharge(
    @PrimaryKey val id: Long = 0,
    @ColumnInfo(name = "idRechargeCenter") val idRechargeCenter: String,
    @ColumnInfo(name = "RechargeCenter") val RechargeCenter: String,
    @ColumnInfo(name = "latitude") val latitude: String,
    @ColumnInfo(name = "longitude") val longitude: String,
    @ColumnInfo(name = "rechargeCenterTypeId") val rechargeCenterTypeId: String,
    @ColumnInfo(name = "rechargeCenterType") val rechargeCenterType: String,
    @ColumnInfo(name = "rechargeCenterCategory") val rechargeCenterCategory: String,
    @ColumnInfo(name = "street") val street: String,
    @ColumnInfo(name = "outdoorNumber") val outdoorNumber: String,
    @ColumnInfo(name = "interiorNumber") val interiorNumber: String,
    @ColumnInfo(name = "neighborhood") val neighborhood: String,
    @ColumnInfo(name = "postalCode") val postalCode: Int
)
