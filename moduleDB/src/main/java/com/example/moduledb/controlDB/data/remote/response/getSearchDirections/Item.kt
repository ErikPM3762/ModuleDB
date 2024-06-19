package com.example.moduledb.controlDB.data.remote.response.getSearchDirections

data class Item(
    val address: Address,
    val distance: Int,
    val id: String,
    val language: String,
    val localityType: String,
    val mapView: MapView,
    val position: Position,
    val resultType: String,
    val title: String
)