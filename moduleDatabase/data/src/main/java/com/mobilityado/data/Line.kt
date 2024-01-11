package com.mobilityado.data

data class Line(
    val id: String,
    val idSAE: String,
    val description: String,
    val localCompany: String,
    val color: String,
    val brands: List<Brand>,
    val stops: List<Stop>,
    val path: List<List<Path>>
)