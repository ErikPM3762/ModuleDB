package com.example.moduledb.controlDB.data.remote.response.getSearchDirections

data class Address(
    val city: String,
    val countryCode: String,
    val countryName: String,
    val county: String,
    val district: String,
    val label: String,
    val postalCode: String,
    val state: String,
    val stateCode: String,
    val street: String
)