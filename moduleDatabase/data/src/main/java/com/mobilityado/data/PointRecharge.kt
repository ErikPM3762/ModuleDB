package com.mobilityado.data

data class PointRecharge (
    val idRechargeCenter: String,
    val RechargeCenter: String,
    val latitude: Double,
    val longitude: Double,
    val rechargeCenterTypeId: String,
    val rechargeCenterType: String,
    val rechargeCenterCategory: String,
    val street: String,
    val outdoorNumber: String,
    val interiorNumber: String,
    val neighborhood: String,
    val postalCode: Int
    )