package com.example.moduledb.controlDB.data.remote.request


data class StopsRequest(
    val idFront: Int,
    val country: String,
    val state: String,
    val cityOrTown: String,
    val idLocalCompany: String,
    val idbusLine: String,
    val idBrand: String
)
