package com.example.moduledb.controlDB.data.models

import com.google.gson.annotations.SerializedName



data class MDbPORechargeResponse(
    @SerializedName("idRechargeCenter")
    val idRechargeCenter: String?,
    @SerializedName("RechargeCenter")
    val RechargeCenter: String?,
    @SerializedName("latitude")
    val latitude: String?,
    @SerializedName("longitude")
    val longitude: String?,
    @SerializedName("rechargeCenterTypeId")
    val rechargeCenterTypeId: String?,
    @SerializedName("rechargeCenterType")
    val rechargeCenterType: String?,
    @SerializedName("rechargeCenterCategory")
    val rechargeCenterCategory: String?,
    @SerializedName("street")
    val street: String?,
    @SerializedName("outdoorNumber")
    val outdoorNumber: String?,
    @SerializedName("interiorNumber")
    val interiorNumber: String?,
    @SerializedName("neighborhood")
    val neighborhood: String?,
    @SerializedName("postalCode")
    val postalCode: Int?
)
