package com.example.moduledb.controlDB.data.remote.models

import com.example.moduledb.controlDB.data.local.entities.BusStopBrandsEntity


data class MDBStops(
    val idBusStop: Int,
    val desBusStop: String?,
    val coordinates: List<String>?,
    val buslineCrossing: List<String>?,
    val brands: List<BusStopBrandsEntity>?
)
