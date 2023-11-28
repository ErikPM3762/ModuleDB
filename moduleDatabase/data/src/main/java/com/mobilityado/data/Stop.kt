package com.mobilityado.data

data class Stop(
    val idLine: Int = -1,
    val id: Int = 0,
    val desBusStop: String = "",
    val longitude: Double = 0.0,
    val latitude: Double = 0.0,
    val order: Int = 0
)
