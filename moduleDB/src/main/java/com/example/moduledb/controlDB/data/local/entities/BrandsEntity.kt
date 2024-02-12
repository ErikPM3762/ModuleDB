package com.example.moduledb.controlDB.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BrandsEntity(
    @PrimaryKey  val idBrand: String,
    val desBrand: String
)