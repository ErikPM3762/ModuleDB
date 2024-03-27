package com.example.moduledb.controlDB.data.remote.request


data class DetailLineRequest(
    val cityOrTown: String,
    val country: String,
    val idBusLine: String,
    val pathIdBusLine: String,
    val idFront: Int,
    val idLocalCompany: String,
    val state: String
)
