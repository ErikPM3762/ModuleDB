package com.example.moduledb.controlDB.data.local.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moduledb.controlDB.data.remote.response.lines.BackTrip
import com.example.moduledb.controlDB.data.remote.response.lines.GeographicDataStructure
import com.example.moduledb.controlDB.data.remote.response.lines.OutTrip
import com.example.moduledb.controlDB.utils.Converters
import com.google.gson.annotations.SerializedName


@Entity
@TypeConverters(Converters::class)
data class MDbLinesDetail(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id", defaultValue = "0") val id: Long = 0,
    @ColumnInfo(name = "idBusSAE")
    val idBusSAE: String?,
    @ColumnInfo(name = "color")
    val color: String?,
    @ColumnInfo(name = "distance")
    val distance: String?,
    @TypeConverters(Converters::class)
    @ColumnInfo(name = "outTrip")
    val outTrip: OutTripEntity,
    @TypeConverters(Converters::class)
    @ColumnInfo(name = "backTrip")
    val backTrip: BackTripEntity?,
    @ColumnInfo(name = "desLocalCompany")
    val desLocalCompany: String?,
    @ColumnInfo(name = "scale")
    val scale: String?,
    @ColumnInfo(name = "idBusLine")
    val idBusLine: String?,
    @ColumnInfo(name = "localCompany")
    val localCompany: String?,
    @TypeConverters(Converters::class)
    @ColumnInfo(name = "geographicDataStructure")
    val geographicDataStructure: GeographicEntity?,
    @ColumnInfo(name = "descBusLine")
    val descBusLine: String?,
    @ColumnInfo(name = "brands")
    val brands: String?
)

