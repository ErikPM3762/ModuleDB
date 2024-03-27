package com.example.moduledb.controlDB.domain.models

import com.example.moduledb.controlDB.data.local.entities.BusStopBrandsEntity


data class MDBDetailStop(
    val idBusStop: String,
    val desBusStop: String?,
    val coordinates: List<String>?,
    val buslineCrossing: List<String>?,
    val brands: List<BusStopBrandsEntity>?
)
