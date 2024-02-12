package com.example.moduledb.controlDB.data.remote.request


data class DetailLineAwseRequest(
    val cityOrTown: String,
    val country: String,
    val idbusLine: String,
    val idFront: Int,
    val idLocalCompany: String,
    val state: String,
    val idLiferayCompany: Int

)
