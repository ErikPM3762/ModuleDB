package com.mobilityado.data

data class PointOfInterest(
    val id: String,
    val name: String,
    val description: String,
    val address: String,
    val phone: String?,
    val latitude: Double,
    val longitude: Double
)
