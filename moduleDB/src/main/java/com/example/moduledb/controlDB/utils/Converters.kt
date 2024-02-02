package com.example.moduledb.controlDB.utils

import androidx.room.TypeConverter
import com.example.moduledb.controlDB.data.local.entities.BrandEntity
import com.example.moduledb.controlDB.data.local.entities.BusStopBrandsEntity
import com.example.moduledb.controlDB.data.local.entities.MacroRegionEntity
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
}
