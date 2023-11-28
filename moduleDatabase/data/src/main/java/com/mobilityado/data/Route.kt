package com.mobilityado.data

data class Route(
    val idLine: String,
    val id: String,
    val idSAE: String,
    val descriptionLine: String,
    val descriptionLocalCompany: String,
    val color: String?,
    val brands: List<Brand>
)
