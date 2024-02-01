package com.example.moduledb.controlDB.data.remote.response.stops

import com.google.gson.annotations.SerializedName

data class BusStopBrands(
    @SerializedName("idBrand")
    val idBrand: String,
    @SerializedName("desBrand")
    val desBrand: String
)
