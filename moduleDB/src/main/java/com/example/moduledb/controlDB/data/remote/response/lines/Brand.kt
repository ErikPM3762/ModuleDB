package com.example.moduledb.controlDB.data.remote.response.lines

import android.graphics.Region
import com.example.moduledb.controlDB.data.remote.response.HeaderResponse
import com.example.moduledb.controlDB.data.remote.response.macroRegions.MacroRegion
import com.google.gson.annotations.SerializedName

data class Brand(
    @SerializedName("idBrand") val idBrand: String? = null,
    @SerializedName("desBrand") val desBrand: String? = null
)
