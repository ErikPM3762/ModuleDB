package com.example.moduledb.controlDB.utils

import androidx.room.TypeConverter
import com.example.moduledb.controlDB.data.local.entities.BackTripEntity
import com.example.moduledb.controlDB.data.local.entities.BrandEntity
import com.example.moduledb.controlDB.data.local.entities.BusStopBrandsEntity
import com.example.moduledb.controlDB.data.local.entities.GeographicEntity
import com.example.moduledb.controlDB.data.local.entities.MacroRegionEntity
import com.example.moduledb.controlDB.data.local.entities.OutTripEntity
import com.example.moduledb.controlDB.data.local.entities.RegionEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    @TypeConverter
    fun fromStringToListBrand(value: String?): List<BrandEntity>? {
        val listType = object : TypeToken<List<BrandEntity>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListBrandToString(list: List<BrandEntity>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromStringToListMacroRegion(value: String?): List<MacroRegionEntity>? {
        val listType = object : TypeToken<List<MacroRegionEntity>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListMacroRegionToString(list: List<MacroRegionEntity>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromStringToListRegion(value: String?): List<RegionEntity>? {
        val listType = object : TypeToken<List<RegionEntity>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListRegionToString(list: List<RegionEntity>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringStop(value: String?): List<BusStopBrandsEntity>? {
        if (value.isNullOrEmpty()) return null
        val listType = object : TypeToken<List<BusStopBrandsEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListStop(list: List<BusStopBrandsEntity>?): String? {
        if (list == null) return null
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToOutTrip(value: String?): OutTripEntity? {
        if (value.isNullOrEmpty()) return null
        return Gson().fromJson(value, OutTripEntity::class.java)
    }

    @TypeConverter
    fun fromOutTripToString(outTrip: OutTripEntity?): String? {
        if (outTrip == null) return null
        return Gson().toJson(outTrip)
    }

    @TypeConverter
    fun fromStringToBackTrip(value: String?): BackTripEntity? {
        if (value.isNullOrEmpty()) return null
        return Gson().fromJson(value, BackTripEntity::class.java)
    }

    @TypeConverter
    fun fromBackTripToString(backTrip: BackTripEntity?): String? {
        if (backTrip == null) return null
        return Gson().toJson(backTrip)
    }

    @TypeConverter
    fun fromStringToGeographicEntity(value: String?): GeographicEntity? {
        if (value.isNullOrEmpty()) return null
        return Gson().fromJson(value, GeographicEntity::class.java)
    }

    @TypeConverter
    fun fromGeographicEntityToString(geographicEntity: GeographicEntity?): String? {
        if (geographicEntity == null) return null
        return Gson().toJson(geographicEntity)
    }

}
