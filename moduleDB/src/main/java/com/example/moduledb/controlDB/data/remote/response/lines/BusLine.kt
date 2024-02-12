package com.example.moduledb.controlDB.data.remote.response.lines

import com.google.gson.annotations.SerializedName

data class BusLine(
    val idBusSAE: String?,
    val color: String?,
    val distance: Any?,
    val outTrip: OutTrip,
    val backTrip: BackTrip?,
    val desLocalCompany: String?,
    val scale: Any?,
    val idBusLine: String?,
    val localCompany: String?,
    @SerializedName("geographic_data_structure")
    val geographicDataStructure: GeographicDataStructure,
    val descBusLine: String?,
    val brands: String?
)
