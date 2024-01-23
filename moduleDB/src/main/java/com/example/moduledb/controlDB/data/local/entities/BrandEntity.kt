package com.example.moduledb.controlDB.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BrandEntity(
    @PrimaryKey val idBrand: Long,
    val desBrand: String,
    val parentId: String
)