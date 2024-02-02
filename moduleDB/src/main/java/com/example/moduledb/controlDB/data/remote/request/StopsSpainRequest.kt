package com.example.moduledb.controlDB.data.remote.request


data class StopsSpainRequest(
    val idFront: Int,
    val country: String,
    val state: String,
    val cityOrTown: String,
    val idLocalCompany: String
)
