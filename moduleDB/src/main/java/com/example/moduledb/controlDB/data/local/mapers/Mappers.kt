package com.example.moduledb.controlDB.data.local.mapers


import com.example.moduledb.controlDB.data.local.entities.MDbDetailStops
import com.example.moduledb.controlDB.data.local.entities.MDbListLines
import com.example.moduledb.controlDB.data.local.entities.MDbLinesByRegion
import com.example.moduledb.controlDB.data.local.entities.MDbLinesDetail
import com.example.moduledb.controlDB.data.local.entities.MDbListStops
import com.example.moduledb.controlDB.data.local.entities.MDbMacroRegions
import com.example.moduledb.controlDB.data.local.entities.MDbPOIs
import com.example.moduledb.controlDB.data.local.entities.MDbPORecharge
import com.example.moduledb.controlDB.data.local.entities.MDdRegions
import com.example.moduledb.controlDB.domain.models.MDBMacroRegions
import com.example.moduledb.controlDB.domain.models.MDBStops
import com.example.moduledb.controlDB.domain.models.MDbPOIsResponse
import com.example.moduledb.controlDB.domain.models.MDbPORechargeResponse
import com.example.moduledb.controlDB.domain.models.MDBDetailStop


/**
 * Transformacion del objeto para puntos de interes
 */
fun MDbPOIsResponse.toPointsInterest(): MDbPOIs {
    return MDbPOIs(
        id = id?.toLongOrNull() ?: 0L,
        idPointOfInterest = id ?: "N/A",
        pointOfInterest = name ?: "N/A",
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

/**
 * Transformacion del objeto para listado de Macro Regiones
 */
fun MDBMacroRegions.toMacroRegions(): MDbMacroRegions {
    return MDbMacroRegions(
        idMacroRegion = idMacroRegion,
        desMacroRegion = desMacroRegion,
        latitudeMacroRegion = latitudeMacroRegion ?: "N/A",
        longitudeMacroRegion = longitudeMacroRegion ?: "N/A",
        routeCount = 0

    )
}

fun List<MDBMacroRegions>.toMacroRegionList(): List<MDbMacroRegions> {
    return this.map {
        it.toMacroRegions()
    }
}

/**
 * Transformacion del objeto para listado de Lineas por Macro Regiones
 */
fun MDbListLines.toLineByMacroRegion(idMacroRegion: String): MDbListLines {
    return MDbListLines(
    idBusLine = idBusLine,
    idBusSAE = idBusSAE,
    descBusLine = descBusLine,
    desLocalCompany = desLocalCompany,
    color = color,
    brands = brands,
    macroRegions = macroRegions,
    regions = regions,
    idMacroRegion = idMacroRegion
    )
}

fun List<MDbListLines>.toLinesByMacroRegions(idMacroRegion: String): List<MDbListLines> {
    return this.map {
        it.toLineByMacroRegion(idMacroRegion)
    }
}

/**
 * Transformacion del objeto para listado de Regiones
 */
fun MDdRegions.toRegions(): MDdRegions {
    return MDdRegions(
        idRegion= idRegion,
        desRegion = desRegion,
        latitudeRegion = latitudeRegion,
        longitudeRegion = longitudeRegion,
        idMacroRegion = idMacroRegion,
        desMacroRegion = desMacroRegion,
        latitudeMacroRegion = latitudeMacroRegion,
        longitudeMacroRegion = longitudeMacroRegion,
    )
}

fun List<MDdRegions>.toRegionList(): List<MDdRegions> {
    return this.map {
        it.toRegions()
    }
}

/**
 * Transformacion del objeto para listado de Lineas por Regiones
 */
fun MDbLinesByRegion.toLineByRegion(idMacroRegion: String): MDbLinesByRegion {
    return MDbLinesByRegion(
        idBusLine = idBusLine,
        idBusSAE = idBusSAE,
        descBusLine = descBusLine,
        desLocalCompany = desLocalCompany,
        color = color,
        brands = brands,
        macroRegions = macroRegions,
        regions = regions,
        idMacroRegion = idMacroRegion)
}

fun List<MDbLinesByRegion>.toLinesByRegions(idMacroRegion: String): List<MDbLinesByRegion> {
    return this.map {
        it.toLineByRegion(idMacroRegion)
    }
}

/**
 * Transformacion del objeto para paradas Oracle
 */
fun MDBStops.toStop(): MDbListStops {
    return MDbListStops(
        idBusStop = idBusStop,
        desBusStop = desBusStop,
        coordinates = coordinates,
        buslineCrossing = buslineCrossing,
        brands = brands)
}

fun List<MDBStops>.toStop(): List<MDbListStops> {
    return this.map {
        it.toStop()
    }
}

/**
 * Transformacion del objeto para listado de Macro Regiones
 */
fun MDbLinesDetail.toDetailLine(): MDbLinesDetail {
    return MDbLinesDetail(
        idBusSAE = idBusSAE,
        color = color,
        distance = distance,
        outTrip = outTrip,
        backTrip = backTrip,
        descBusLine = descBusLine,
        scale = scale,
        idBusLine = idBusLine,
        localCompany = localCompany,
        geographicDataStructure = geographicDataStructure,
        desLocalCompany = desLocalCompany,
        brands = brands
    )
}

fun List<MDbLinesDetail>.toDetailLineList(): List<MDbLinesDetail> {
    return this.map {
        it.toDetailLine()
    }
}

/**
 * Transformacion del objeto para detalle de parada
 */
private fun MDBDetailStop.toRoomDetailStopConverter(): MDbDetailStops {
    return MDbDetailStops(
        id = idBusStop.toLongOrNull() ?: 0L,
        idBusStop = idBusStop ?: "0",
        desBusStop = desBusStop ?: "N/A",
        coordinates = coordinates,
        buslineCrossing = buslineCrossing,
        brands = brands
    )
}

fun MDBDetailStop.toRoomDetailStop(): MDbDetailStops {
    return toRoomDetailStopConverter()
}

/**
 * Transformacion del objeto para detalle de parada
 */
private fun MDbDetailStops.toDetailStopConverter(): MDBDetailStop {
    return MDBDetailStop(
        idBusStop = idBusStop ?: "0",
        desBusStop = desBusStop ?: "N/A",
        coordinates = coordinates,
        buslineCrossing = buslineCrossing,
        brands = brands
    )
}

fun MDbDetailStops.toDetailStop(): MDBDetailStop {
    return toDetailStopConverter()
}
