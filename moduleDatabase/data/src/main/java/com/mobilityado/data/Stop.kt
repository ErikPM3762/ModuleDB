package com.mobilityado.data

data class Stop(
    val idLine: Int,
    val idRoute: Int,
    val id: Int,
    val description: String,
    val longitude: Double,
    val latitude: Double,
    val order: Int = 0
)
