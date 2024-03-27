package com.example.moduledb.controlDB.data.local.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moduledb.controlDB.utils.Converters


@Entity
data class MDbListLines(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id", defaultValue = "0") val id: Long = 0,
    val idBusLine: String,
    val idBusSAE: String,
    val descBusLine: String,
    val desLocalCompany: String,
    val color: String? = null,
    @TypeConverters(Converters::class)
    val brands: List<BrandEntity>?,
    @TypeConverters(Converters::class)
    val macroRegions: List<MacroRegionEntity>?,
    @TypeConverters(Converters::class)
    val regions: List<RegionEntity>?,
    val idMacroRegion : String
)