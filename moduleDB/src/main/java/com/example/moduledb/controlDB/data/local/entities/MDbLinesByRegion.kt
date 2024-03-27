package com.example.moduledb.controlDB.data.local.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moduledb.controlDB.utils.Converters


@Entity
data class MDbLinesByRegion(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id", defaultValue = "0") val id: Long = 0,
    @ColumnInfo(name = "idBusLine") val idBusLine: String,
    @ColumnInfo(name = "idBusSAE") val idBusSAE: String,
    @ColumnInfo(name = "descBusLine") val descBusLine: String,
    @ColumnInfo(name = "desLocalCompany") val desLocalCompany: String,
    @ColumnInfo(name = "color") val color: String? = null,
    @TypeConverters(Converters::class)
    @ColumnInfo(name = "brands") val brands: List<BrandEntity>?,
    @TypeConverters(Converters::class)
    @ColumnInfo(name = "macroRegions") val macroRegions: List<MacroRegionEntity>?,
    @TypeConverters(Converters::class)
    @ColumnInfo(name = "regions") val regions: List<RegionEntity>?,
    @ColumnInfo(name = "idMacroRegion") var idMacroRegion: String?
)

