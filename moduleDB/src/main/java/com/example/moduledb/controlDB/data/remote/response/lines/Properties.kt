package com.example.moduledb.controlDB.data.remote.response.lines

data class Properties(
    val busLineCrossing: Any?,
    val color: String,
    val desBusStop: String = "",
    val idBusLine: String,
    val idBusSAE: String,
    val idBusStop: String,
    val node: String
)
