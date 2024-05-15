package com.example.moduledb.controlDB.data.local.mapers


import com.example.moduledb.controlDB.data.local.entities.BackTripEntity
import com.example.moduledb.controlDB.data.local.entities.BrandEntity
import com.example.moduledb.controlDB.data.local.entities.BrandsEntity
import com.example.moduledb.controlDB.data.local.entities.BusStopBrandsEntity
import com.example.moduledb.controlDB.data.local.entities.BusStopEntity
import com.example.moduledb.controlDB.data.local.entities.DayEntity
import com.example.moduledb.controlDB.data.local.entities.FeatureEntity
import com.example.moduledb.controlDB.data.local.entities.GeographicEntity
import com.example.moduledb.controlDB.data.local.entities.GeometryEntity
import com.example.moduledb.controlDB.data.local.entities.MDbDetailStops
import com.example.moduledb.controlDB.data.local.entities.MDbLinesByRegion
import com.example.moduledb.controlDB.data.local.entities.MDbLinesDetail
import com.example.moduledb.controlDB.data.local.entities.MDbListLines
import com.example.moduledb.controlDB.data.local.entities.MDbListStops
import com.example.moduledb.controlDB.data.local.entities.MDbListTheoricByTypeStop
import com.example.moduledb.controlDB.data.local.entities.MDbMacroRegions
import com.example.moduledb.controlDB.data.local.entities.MDbPOIs
import com.example.moduledb.controlDB.data.local.entities.MDbPORecharge
import com.example.moduledb.controlDB.data.local.entities.MDbRouteEntity
import com.example.moduledb.controlDB.data.local.entities.MDdRegions
import com.example.moduledb.controlDB.data.local.entities.MacroRegionEntity
import com.example.moduledb.controlDB.data.local.entities.OutTripEntity
import com.example.moduledb.controlDB.data.local.entities.PropertiesEntity
import com.example.moduledb.controlDB.data.local.entities.RegionEntity
import com.example.moduledb.controlDB.data.local.entities.ScheduleEntity
import com.example.moduledb.controlDB.data.local.entities.StyleEntity
import com.example.moduledb.controlDB.domain.models.MDBDetailStop
import com.example.moduledb.controlDB.domain.models.MDBTheoricByTypeStop
import com.example.services.data.response.lines.BackTrip
import com.example.services.data.response.lines.Brand
import com.example.services.data.response.lines.Feature
import com.example.services.data.response.lines.Geographic
import com.example.services.data.response.lines.Geometry
import com.example.services.data.response.lines.LinesByRegion
import com.example.services.data.response.lines.LinesDetail
import com.example.services.data.response.lines.ListLines
import com.example.services.data.response.lines.OutTrip
import com.example.services.data.response.lines.Properties
import com.example.services.data.response.lines.Region
import com.example.services.data.response.lines.Style
import com.example.services.data.response.macroRegions.MacroRegion
import com.example.services.data.response.macroRegions.MacroRegions
import com.example.services.data.response.pointsRecharge.PORecharge
import com.example.services.data.response.regions.Regions
import com.example.services.data.response.routes.Route
import com.example.services.data.response.stops.BusStopBrands
import com.example.services.data.response.stops.Stops
import com.example.services.data.response.teroicByStop.BusStop
import com.example.services.data.response.teroicByStop.Day
import com.example.services.data.response.teroicByStop.Schedule
import com.example.services.domain.models.DetailStop
import com.example.services.domain.models.POIsResponse
import com.example.services.domain.models.TheoricByTypeStop


/**
 * Transformacion del objeto para puntos de interes
 */
fun POIsResponse.toPointsInterest(): MDbPOIs {
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

fun List<POIsResponse>.toPointsInterestList(): List<MDbPOIs> {
    return this.map {
        it.toPointsInterest()
    }
}

/**
 * Transformacion del objeto para puntos de recarga
 */
fun PORecharge.toPointsRecharge(): MDbPORecharge {
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

fun List<PORecharge>.toPointsRechargeList(): List<MDbPORecharge> {
    return this.map { it.toPointsRecharge() }
}

/**
 * Transformacion del objeto para listado de Macro Regiones
 */
fun MacroRegions.toMacroRegions(): MDbMacroRegions {
    return MDbMacroRegions(
        idMacroRegion = idMacroRegion,
        desMacroRegion = desMacroRegion,
        latitudeMacroRegion = latitudeMacroRegion ?: "N/A",
        longitudeMacroRegion = longitudeMacroRegion ?: "N/A",
        routeCount = 0

    )
}

fun List<MacroRegions>.toMacroRegionsList(): List<MDbMacroRegions> {
    return this.map {
        it.toMacroRegions()
    }
}

/**
 * Transformacion del objeto para listado de Lineas por Macro Regiones
 */
fun ListLines.toLineByMacroRegion(idMacroRegion: String): MDbListLines {
    return MDbListLines(
        idBusLine = idBusLine,
        idBusSAE = idBusSAE,
        descBusLine = descBusLine,
        desLocalCompany = desLocalCompany,
        color = color,
        brands = brands?.toBrandList(),
        macroRegions = macroRegions?.toMacroRegionList(),
        regions = regions?.toRegionList(),
        idMacroRegion = idMacroRegion
    )
}

fun List<ListLines>.toLinesByMacroRegions(idMacroRegion: String): List<MDbListLines> {
    return this.map {
        it.toLineByMacroRegion(idMacroRegion)
    }
}

/**
 * Transformacion del objeto para listado de Regiones
 */
fun Regions.toRegions(): MDdRegions {
    return MDdRegions(
        idRegion = idRegion,
        desRegion = desRegion,
        latitudeRegion = latitudeRegion,
        longitudeRegion = longitudeRegion,
        idMacroRegion = idMacroRegion,
        desMacroRegion = desMacroRegion,
        latitudeMacroRegion = latitudeMacroRegion,
        longitudeMacroRegion = longitudeMacroRegion,
    )
}

fun List<Regions>.toRegionsList(): List<MDdRegions> {
    return this.map {
        it.toRegions()
    }
}

/**
 * Transformacion del objeto para listado de Lineas por Regiones
 */
fun LinesByRegion.toLineByRegion(idMacroRegion: String): MDbLinesByRegion {
    return MDbLinesByRegion(
        idBusLine = idBusLine,
        idBusSAE = idBusSAE,
        descBusLine = descBusLine,
        desLocalCompany = desLocalCompany,
        color = color,
        brands = brands?.toBrandList(),
        macroRegions = macroRegions?.toMacroRegionList(),
        regions = regions?.toRegionList(),
        idMacroRegion = idMacroRegion
    )
}

fun List<LinesByRegion>.toLinesByRegions(idMacroRegion: String): List<MDbLinesByRegion> {
    return this.map {
        it.toLineByRegion(idMacroRegion)
    }
}

/**
 * Transformacion del objeto para paradas Oracle
 */
fun Stops.toStop(): MDbListStops {
    return MDbListStops(
        idBusStop = idBusStop,
        desBusStop = desBusStop,
        coordinates = coordinates,
        buslineCrossing = buslineCrossing,
        brands = brands?.toStopsBrandsList()
    )
}

fun List<Stops>.toStop(): List<MDbListStops> {
    return this.map {
        it.toStop()
    }
}

/**
 * Transformacion del objeto para teoricos por tipo parada AWS
 */
private fun TheoricByTypeStop.toTheoricByTypeStop(idLineGenerate: String, tripCode: String): MDbListTheoricByTypeStop {
    return MDbListTheoricByTypeStop(
        busStop = busStop.toBusStopList(),
        idLineGenerate = idLineGenerate,
        tripCode = tripCode
    )
}

fun TheoricByTypeStop.toRoomTheoricByTypeStop(idLineGenerate: String, tripCode: String): MDbListTheoricByTypeStop {
    return this.toTheoricByTypeStop(idLineGenerate, tripCode)
}

/**
 * Transformacion del objeto para teoricos por tipo parada AWS
 */
private fun TheoricByTypeStop.toTheoricByStop(): MDBTheoricByTypeStop {
    return MDBTheoricByTypeStop(
        busStop = busStop.toBusStopList()
    )
}

fun TheoricByTypeStop.toTheoricByTypeStop(): MDBTheoricByTypeStop {
    return this.toTheoricByStop()
}

/**
 * Transformacion del objeto para listado de Macro Regiones
 */
fun LinesDetail.toDetailLine(): MDbLinesDetail {
    return MDbLinesDetail(
        idBusSAE = idBusSAE,
        color = color,
        distance = distance,
        outTrip = outTrip.toOutrip(),
        backTrip = backTrip?.toBacktrip(),
        descBusLine = descBusLine,
        scale = scale,
        idBusLine = idBusLine,
        localCompany = localCompany,
        geographicDataStructure = geographicDataStructure?.toGeographics(),
        desLocalCompany = desLocalCompany,
        brands = brands,
        pathIdBusLine = pathIdBusLine

    )
}

fun List<LinesDetail>.toDetailLineList(): List<MDbLinesDetail> {
    return this.map {
        it.toDetailLine()
    }
}

/**
 * Transformacion del objeto para listado de Macro Regiones
 */
fun MDbLinesDetail.toDetailLineInvert(): LinesDetail {
    return LinesDetail(
        idBusSAE = idBusSAE,
        color = color,
        distance = distance,
        outTrip = outTrip.toOutripInvert(),
        backTrip = backTrip?.toBacktripInvert(),
        descBusLine = descBusLine,
        scale = scale,
        idBusLine = idBusLine,
        localCompany = localCompany,
        geographicDataStructure = geographicDataStructure?.toGeographicsInvert(),
        desLocalCompany = desLocalCompany,
        brands = brands,
        pathIdBusLine = pathIdBusLine

    )
}

fun List<MDbLinesDetail>.toDetailLineListInvert(): List<LinesDetail> {
    return this.map {
        it.toDetailLineInvert()
    }
}


/**
 * Transformacion del objeto para detalle de parada
 */
private fun DetailStop.toRoomDetailStopConverter(): MDbDetailStops {
    return MDbDetailStops(
        id = idBusStop.toLongOrNull() ?: 0L,
        idBusStop = idBusStop ?: "0",
        desBusStop = desBusStop ?: "N/A",
        coordinates = coordinates,
        buslineCrossing = buslineCrossing,
        brands = brands?.toStopsBrandsList()
    )
}

fun DetailStop.toRoomDetailStop(): MDbDetailStops {
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

private fun Brand.toBrand(): BrandEntity {
    return BrandEntity(
        idBrand = idBrand,
        desBrand = desBrand,
        parentId = parentId
    )
}

fun List<Brand>.toBrandList(): List<BrandEntity> {
    return this.map {
        it.toBrand()
    }
}

private fun Brand.toBrands(): BrandsEntity {
    return BrandsEntity(
        idBrand = idBrand.toString(),
        desBrand = desBrand
    )
}

fun List<Brand>.toBrandsList(): List<BrandsEntity> {
    return this.map {
        it.toBrands()
    }
}

private fun MacroRegion.toMacroRegion(): MacroRegionEntity {
    return MacroRegionEntity(
        idMacroRegion,
        desMacroRegion,
        parentId
    )
}

fun List<MacroRegion>.toMacroRegionList(): List<MacroRegionEntity> {
    return this.map {
        it.toMacroRegion()
    }
}

private fun Region.toRegion(): RegionEntity {
    return RegionEntity(
        idRegion,
        desRegion,
        parentId
    )
}

fun List<Region>.toRegionList(): List<RegionEntity> {
    return this.map {
        it.toRegion()
    }
}

private fun Route.toRoute(): MDbRouteEntity {
    return MDbRouteEntity(
        idBusLine= idBusLine,
        pathIdBusLine = pathIdBusLine,
        pathIdDescription = pathIdDescription,
        direction = direction
    )
}

fun List<Route>.toRouteList(): List<MDbRouteEntity> {
    return this.map {
        it.toRoute()
    }
}

private fun MDbRouteEntity.toRouteInverted(): Route {
    return Route(
        idBusLine= idBusLine,
        pathIdBusLine = pathIdBusLine,
        pathIdDescription = pathIdDescription,
        direction = direction
    )
}

fun List<MDbRouteEntity>.toRouteInvertedList(): List<Route> {
    return this.map {
        it.toRouteInverted()
    }
}


private fun BusStopBrands.toStopsBrands(): BusStopBrandsEntity {
    return BusStopBrandsEntity(
        idBrand.toLong(),
        idBrand,
        desBrand
    )
}

fun List<BusStopBrands>.toStopsBrandsList(): List<BusStopBrandsEntity> {
    return this.map {
        it.toStopsBrands()
    }
}

private fun BusStop.toBusStop(): BusStopEntity {
    return BusStopEntity(
        idBusStop.toLong(),
        idBusStop,
        days.toDayList()
    )
}

fun List<BusStop>.toBusStopList(): List<BusStopEntity> {
    return this.map {
        it.toBusStop()
    }
}

private fun Day.toDay(): DayEntity {
    return DayEntity(
        dayType = dayType,
        schedules = schedules.toScheduleList()
    )
}

fun List<Day>.toDayList(): List<DayEntity> {
    return this.map {
        it.toDay()
    }
}

private fun Schedule.toSchedule(): ScheduleEntity {
    return ScheduleEntity(
        dayTime = dayTime,
        pathIdBusLine = pathIdBusLine,
        arrivalBusStop = arrivalBusStop,
        idBusStop = idBusStop
    )
}

fun List<Schedule>.toScheduleList(): List<ScheduleEntity> {
    return this.map {
        it.toSchedule()
    }
}

private fun OutTrip.toOutrip(): OutTripEntity {
    return OutTripEntity(
        features = features.toFeatureList(),
        type = type,
    )
}

private fun OutTripEntity.toOutripInvert(): OutTrip {
    return OutTrip(
        features = features.toFeatureListInvert(),
        type = type,
    )
}

private fun BackTrip.toBacktrip(): BackTripEntity {
    return BackTripEntity(
        features = features.toFeatureList(),
        type = type,
    )
}

private fun BackTripEntity.toBacktripInvert(): BackTrip {
    return BackTrip(
        features = features.toFeatureListInvert(),
        type = type,
    )
}

private fun Feature.toFeature(): FeatureEntity {
    return FeatureEntity(
        geometry = geometry.toGeometry(),
        properties = properties.toProperties(),
        type = type,
        style = style?.toStyle()
    )
}


fun List<Feature>.toFeatureList(): List<FeatureEntity> {
    return this.map {
        it.toFeature()
    }
}

private fun FeatureEntity.toFeatureInvert(): Feature {
    return Feature(
        geometry = geometry.toGeometryInvert(),
        properties = properties.toPropertiesInvert(),
        type = type,
        style = style?.toStyleInvert()
    )
}


fun List<FeatureEntity>.toFeatureListInvert(): List<Feature> {
    return this.map {
        it.toFeatureInvert()
    }
}

private fun Geometry.toGeometry(): GeometryEntity {
    return GeometryEntity(
        coordinates = coordinates,
        type = type
    )
}

private fun Style.toStyle(): StyleEntity {
    return StyleEntity(
        strokeWidth= strokeWidth,
        fill= fill,
        fillOpacity= fillOpacity
    )
}

private fun Geographic.toGeographics(): GeographicEntity {
    return GeographicEntity(
        initialMapCoordinates= initialMapCoordinates
    )
}


private fun GeometryEntity.toGeometryInvert(): Geometry {
    return Geometry(
        coordinates = coordinates,
        type = type
    )
}

private fun StyleEntity.toStyleInvert(): Style {
    return Style(
        strokeWidth= strokeWidth,
        fill= fill,
        fillOpacity= fillOpacity
    )
}

private fun GeographicEntity.toGeographicsInvert(): Geographic {
    return Geographic(
        initialMapCoordinates= initialMapCoordinates
    )
}



private fun Properties.toProperties(): PropertiesEntity {
    return PropertiesEntity(
        busLineCrossing = busLineCrossing,
        color= color,
        desBusStop= desBusStop,
        idBusLine= idBusLine,
        idBusSAE= idBusSAE,
        idBusStop= idBusStop,
        node=  node,
        brands = brands?.toBrandsList()
    )
}

private fun PropertiesEntity.toPropertiesInvert(): Properties {
    return Properties(
        busLineCrossing = busLineCrossing,
        color= color!!,
        desBusStop= desBusStop,
        idBusLine= idBusLine,
        idBusSAE= idBusSAE,
        idBusStop= idBusStop,
        node=  node,
        brands = brands?.toBrandsListInvert()
    )
}

private fun BrandsEntity.toBrandsInvert(): Brand {
    return Brand(
        idBrand = idBrand.toLong(),
        desBrand = desBrand,
        ""
    )
}

fun List<BrandsEntity>.toBrandsListInvert(): List<Brand> {
    return this.map {
        it.toBrandsInvert()
    }
}







