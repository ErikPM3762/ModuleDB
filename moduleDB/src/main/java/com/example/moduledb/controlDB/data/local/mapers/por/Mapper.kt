package com.example.moduledb.controlDB.data.local.mapers.por

import com.example.moduledb.controlDB.data.local.entities.MDbPORecharge
import com.example.services.data.response.pointsRecharge.PORecharge

/**
 * Transformacion del objeto para puntos de recarga
 */
fun MDbPORecharge.toPointsRechargeInverted(): PORecharge {
    return PORecharge(
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

fun List<MDbPORecharge>.toPointsRechargeListInverted(): List<PORecharge> {
    return this.map { it.toPointsRechargeInverted() }
}