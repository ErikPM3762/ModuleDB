package com.example.moduledb.controlDB.data.remote.response.lines

import com.example.moduledb.controlDB.data.local.entities.BrandsEntity

data class Properties(
    val busLineCrossing: Any?,
    val color: String,
    val desBusStop: String? = "",
    val idBusLine: String,
    val idBusSAE: String? = "",
    val idBusStop: String,
    val node: String?,
    val brands: List<BrandsEntity>?= emptyList(),
)
