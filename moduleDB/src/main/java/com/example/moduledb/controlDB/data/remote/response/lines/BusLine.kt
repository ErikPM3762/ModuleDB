package com.example.moduledb.controlDB.data.remote.response.lines

import com.google.gson.annotations.SerializedName

data class BusLine(
    val backTrip: Any,
    val color: String?,
    val desLocalCompany: String,
    val descBusLine: String,
    val brands: String?,
    val distance: Any,
    @SerializedName("geographic_data_structure")
    val geographicDataStructure: GeographicDataStructure,
    val idBusLine: String,
    val idBusSAE: String,
    val localCompany: String,
    val outTrip: OutTrip,
    val scale: Any
)
