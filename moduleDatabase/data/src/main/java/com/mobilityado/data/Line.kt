package com.mobilityado.data

data class Line(
    val idBusLine: String,
    val idBusSAE: String,
    val descriptionLine: String,
    val descriptionLocalCompany: String,
    val color: String?,
    val brand: String
)