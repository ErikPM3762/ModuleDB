package com.example.moduledb.controlDB.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class MDbVersionInfo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "pointInterestVersion") var pointInterestVersion: Long? = 0,
    @ColumnInfo(name = "pointRechargeVersion") var pointRechargeVersion: Long? = 0,
)
