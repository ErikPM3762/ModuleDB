package com.example.moduledb.controlDB.data.local


import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameTable
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import com.example.moduledb.controlDB.data.local.daos.MDbLinesByMacroRegionDao
import com.example.moduledb.controlDB.data.local.daos.MDbLinesByRegionDao
import com.example.moduledb.controlDB.data.local.daos.MDbLinesDetailDao
import com.example.moduledb.controlDB.data.local.daos.MDbMacroRegionsDao
import com.example.moduledb.controlDB.data.local.daos.MDbPOIsDao
import com.example.moduledb.controlDB.data.local.daos.MDbPORechargeDao
import com.example.moduledb.controlDB.data.local.daos.MDbRegionsDao
import com.example.moduledb.controlDB.data.local.daos.MDbStopsDao
import com.example.moduledb.controlDB.data.local.daos.MDbVersionInfoDao
import com.example.moduledb.controlDB.data.local.entities.BrandEntity
import com.example.moduledb.controlDB.data.local.entities.BusStopBrandsEntity
import com.example.moduledb.controlDB.data.local.entities.MDbListLines
import com.example.moduledb.controlDB.data.local.entities.MDbLinesByRegion
import com.example.moduledb.controlDB.data.local.entities.MDbLinesDetail
import com.example.moduledb.controlDB.data.local.entities.MDbListStops
import com.example.moduledb.controlDB.data.local.entities.MDbMacroRegions
import com.example.moduledb.controlDB.data.local.entities.MDbPOIs
import com.example.moduledb.controlDB.data.local.entities.MDbPORecharge
import com.example.moduledb.controlDB.data.local.entities.MDbVersionInfo
import com.example.moduledb.controlDB.data.local.entities.MDdRegions
import com.example.moduledb.controlDB.data.local.entities.MacroRegionEntity
import com.example.moduledb.controlDB.data.local.entities.RegionEntity
import com.example.moduledb.controlDB.utils.Converters


/**
 * En este apartado realizaremos la automigracion cada que se agregue una nueva tabla a nuestra base de datos.
 */
@Database(
    entities = [
        MDbPOIs::class,
        MDbPORecharge::class,
        MDbVersionInfo::class,
        MDbMacroRegions::class,
        MDbListLines::class,
        BrandEntity::class,
        RegionEntity::class,
        MacroRegionEntity::class,
        MDdRegions::class,
        MDbLinesByRegion::class,
        MDbListStops::class,
        BusStopBrandsEntity::class,
        MDbLinesDetail::class
    ],
    version = 14,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3),
        AutoMigration(from = 3, to = 4),
        AutoMigration(from = 4, to = 5),
        AutoMigration(from = 5, to = 6),
        AutoMigration(from = 6, to = 7),
        AutoMigration(from = 7, to = 8),
        AutoMigration(from = 8, to = 9),
        AutoMigration(from = 9, to = 10),
        AutoMigration(from = 10, to = 11),
        AutoMigration(from = 11, to = 12),
        AutoMigration(from = 12, to = 13),
        AutoMigration(from = 13, to = 14)
    ]
)
@TypeConverters(Converters::class)

/**
 * Aqui se realiza la conexion de datos de los Dao correspondientes con el inject DaoModule
 * para proveer las dependencias
 */

abstract class AppDataBase : RoomDatabase() {

    /**
     * Abstract fun para puntos de recarga y puntos de interes
     */
    abstract fun pointsInterest(): MDbPOIsDao
    abstract fun pointsRecharge(): MDbPORechargeDao
    abstract fun versionTable(): MDbVersionInfoDao

    /**
     * Abstract fun para Regiones y macroRegiones
     */
    abstract fun macroRegions(): MDbMacroRegionsDao
    abstract fun regions(): MDbRegionsDao

    /**
     * Abstract fun para lista de lineas por Region y MacroRegion
     */
    abstract fun listMacroRegions(): MDbLinesByMacroRegionDao
    abstract fun listRegions(): MDbLinesByRegionDao

    /**
     * Abstract fun para lista de paradas
     */
    abstract fun listStops(): MDbStopsDao

    /**
     * Abstract fun para lista detalle de linea
     */
    abstract fun listDeatilLine(): MDbLinesDetailDao

    /**
     * Cada que eliminemos alguna columna o renombremos algun campo o tabla tendremos que manejarlo con los spec
     */
    @RenameTable(fromTableName = "MDbListLines", toTableName = "MDbLinesByMacroRegion")
    class Version9AutoMigration : AutoMigrationSpec

}


