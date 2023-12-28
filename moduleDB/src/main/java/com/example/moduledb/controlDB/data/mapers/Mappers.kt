package com.example.moduledb.controlDB.data.mapers

import android.util.Log
import com.example.moduledb.controlDB.data.local.entities.MDbPOIs
import com.example.moduledb.controlDB.data.local.entities.MDbPORecharge
import com.example.moduledb.controlDB.data.models.MDbPOIsResponse
import com.example.moduledb.controlDB.data.models.MDbPORechargeResponse

/**
 * Transformacion del objeto para puntos de interes
 */
fun MDbPOIsResponse.toPointsInterest(): MDbPOIs {
    return MDbPOIs(
        id = id?.toLongOrNull() ?: 0L,
        idPointOfInterest = id ?: "N/A" ,
        pointOfInterest = name ?: "N/A" ,
        descPointOfInterest = description ?: "N/A",
        pointOfInterestAddress = address ?: "N/A",
        pointOfInterestPhone = phone ?: "N/A",
        latitude = latitude ?: "N/A",
        longitude = longitude ?: "N/A"
    )
}

fun List<MDbPOIsResponse>.toPointsInterestList(): List<MDbPOIs> {
    return this.map {
        it.toPointsInterest()
    }
}

/**
 * Transformacion del objeto para puntos de recarga
 */
fun MDbPORechargeResponse.toPointsRecharge(): MDbPORecharge {
    return MDbPORecharge(
        id = idRechargeCenter?.toLongOrNull() ?: 0L,
        idRechargeCenter = idRechargeCenter ?: "N/A",
        RechargeCenter = RechargeCenter ?: "N/A",
        latitude = latitude ?: "N/A",
        longitude = longitude ?: "N/A",
        rechargeCenterTypeId = rechargeCenterTypeId ?: "N/A",
        rechargeCenterType = rechargeCenterType ?: "N/A",
        rechargeCenterCategory = rechargeCenterCategory ?: "N/A",
        street = street ?: "N/A",
        outdoorNumber = outdoorNumber ?: "N/A",
        interiorNumber = interiorNumber ?: "N/A",
        neighborhood = neighborhood ?: "N/A",
        postalCode = postalCode ?: 0
    )
}

fun List<MDbPORechargeResponse>.toPointsRechargeList(): List<MDbPORecharge> {
    return this.map { it.toPointsRecharge() }
}