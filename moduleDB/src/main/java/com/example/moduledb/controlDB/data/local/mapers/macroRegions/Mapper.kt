package com.example.moduledb.controlDB.data.local.mapers.macroRegions

import com.example.moduledb.controlDB.data.local.entities.BrandEntity
import com.example.moduledb.controlDB.data.local.entities.MDbListLines
import com.example.moduledb.controlDB.data.local.entities.MDbMacroRegions
import com.example.moduledb.controlDB.data.local.entities.MDbPORecharge
import com.example.moduledb.controlDB.data.local.entities.MacroRegionEntity
import com.example.moduledb.controlDB.data.local.entities.RegionEntity
import com.example.moduledb.controlDB.data.local.mapers.toBrandList
import com.example.moduledb.controlDB.data.local.mapers.toMacroRegionList
import com.example.moduledb.controlDB.data.local.mapers.toRegionList
import com.example.services.data.response.lines.Brand
import com.example.services.data.response.lines.ListLines
import com.example.services.data.response.lines.Region
import com.example.services.data.response.macroRegions.MacroRegion
import com.example.services.data.response.macroRegions.MacroRegions
import com.example.services.data.response.pointsRecharge.PORecharge

/**
 * Transformacion del objeto para puntos de recarga
 */
fun MDbMacroRegions.toConverterPrivate(): MacroRegions {
    return MacroRegions(
        idMacroRegion = idMacroRegion,
        desMacroRegion = desMacroRegion,
        latitudeMacroRegion = latitudeMacroRegion ?: "N/A",
        longitudeMacroRegion = longitudeMacroRegion ?: "N/A",
        routeCount = 0
    )
}

fun List<MDbMacroRegions>.toConverter(): List<MacroRegions> {
    return this.map { it.toConverterPrivate() }
}

/**
 * Transformacion del objeto para listado de Lineas por Macro Regiones
 */
fun MDbListLines.toLineByMacroRegion(): ListLines {
    return ListLines(
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

fun List<MDbListLines>.toConverterListLines(): List<ListLines> {
    return this.map {
        it.toLineByMacroRegion()
    }
}

private fun BrandEntity.toBrand(): Brand {
    return Brand(
        idBrand = idBrand,
        desBrand = desBrand,
        parentId = parentId
    )
}

fun List<BrandEntity>.toBrandList(): List<Brand> {
    return this.map {
        it.toBrand()
    }
}

private fun MacroRegionEntity.toMacroRegion(): MacroRegion {
    return MacroRegion(
        idMacroRegion,
        desMacroRegion,
        parentId
    )
}

fun List<MacroRegionEntity>.toMacroRegionList(): List<MacroRegion> {
    return this.map {
        it.toMacroRegion()
    }
}

private fun RegionEntity.toRegion(): Region {
    return Region(
        idRegion,
        desRegion,
        parentId
    )
}

fun List<RegionEntity>.toRegionList(): List<Region> {
    return this.map {
        it.toRegion()
    }
}
