package com.example.moduledb.controlDB.data.remote.response.pointsRecharge

import com.google.gson.annotations.SerializedName

data class RechargingCenter(
    @SerializedName("RechargeCenter")
    val rechargeCenter: String?,
    @SerializedName("idRechargeCenter")
    val id: Int,
    @SerializedName("interiorNumber")
    val interiorNumber: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("neighborhood")
    val neighborhood: String,
    @SerializedName("outdoorNumber")
    val outdoorNumber: String,
    @SerializedName("postalCode")
    val postalCode: String,
    @SerializedName("rechargeCenterCategory")
    val category: String,
    @SerializedName("rechargeCenterType")
    val type: String?,
    @SerializedName("rechargeCenterTypeId")
    val typeId: String,
    @SerializedName("street")
    val street: String
)