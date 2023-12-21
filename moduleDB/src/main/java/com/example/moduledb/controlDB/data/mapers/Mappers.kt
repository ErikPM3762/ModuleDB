package com.example.moduledb.controlDB.data.mapers

import android.util.Log
import com.example.moduledb.controlDB.data.entities.MDbPOIs
import com.example.moduledb.controlDB.data.entities.MDbPORecharge
import com.example.moduledb.controlDB.data.models.MDbPOIsResponse
import com.example.moduledb.controlDB.data.models.MDbPORechargeResponse

/**
 * Transformacion del objeto para puntos de interes
 */
fun MDbPOIsResponse.toPointsInterest(): MDbPOIs {
    return MDbPOIs(
        id = id?.toLongOrNull() ?: 0L,
        idPointOfInterest = id!! ,
        pointOfInterest = name!! ,
        descPointOfInterest = description!!,
        pointOfInterestAddress = address!!,
        pointOfInterestPhone = phone ?: "N/A",
        latitude = latitude!!,
        longitude = longitude!!
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
        id = this.idRechargeCenter.toLongOrNull() ?: 0L,
        idRechargeCenter = idRechargeCenter,
        RechargeCenter =RechargeCenter,
        latitude = latitude,
        longitude = longitude,
        rechargeCenterTypeId = rechargeCenterTypeId,
        rechargeCenterType = rechargeCenterType,
        rechargeCenterCategory = rechargeCenterCategory,
        street = street,
        outdoorNumber = outdoorNumber,
        interiorNumber = interiorNumber,
        neighborhood = neighborhood,
        postalCode = postalCode
    )
}

fun List<MDbPORechargeResponse>.toPointsRechargeList(): List<MDbPORecharge> {
    return this.map { it.toPointsRecharge() }
}