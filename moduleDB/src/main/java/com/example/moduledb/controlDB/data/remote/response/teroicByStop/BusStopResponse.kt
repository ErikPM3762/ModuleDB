package com.example.moduledb.controlDB.data.remote.response.teroicByStop

import com.example.moduledb.controlDB.data.local.entities.DayEntity

data class BusStopResponse(
    val idBusStop: String,
    val days: List<DayEntity>
)
