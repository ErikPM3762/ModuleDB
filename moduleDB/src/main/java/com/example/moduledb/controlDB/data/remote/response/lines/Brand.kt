package com.example.moduledb.controlDB.data.remote.response.lines

import com.google.gson.annotations.SerializedName

data class Brand(
    @SerializedName("idBrand") val idBrand: String? = null,
    @SerializedName("desBrand") val desBrand: String? = null
)