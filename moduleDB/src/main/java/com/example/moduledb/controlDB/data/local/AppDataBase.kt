package com.example.moduledb.controlDB.data.local


import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameTable
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import com.example.moduledb.controlDB.data.local.daos.MDbListLinesDao
import com.example.moduledb.controlDB.data.local.daos.MDbMacroRegionsDao
import com.example.moduledb.controlDB.data.local.daos.MDbPOIsDao
import com.example.moduledb.controlDB.data.local.daos.MDbPORechargeDao
import com.example.moduledb.controlDB.data.local.daos.MDbVersionInfoDao
import com.example.moduledb.controlDB.data.local.entities.BrandEntity
import com.example.moduledb.controlDB.data.local.entities.MDbListLines
import com.example.moduledb.controlDB.data.local.entities.MDbMacroRegions
import com.example.moduledb.controlDB.data.local.entities.MDbPOIs
import com.example.moduledb.controlDB.data.local.entities.MDbPORecharge
import com.example.moduledb.controlDB.data.local.entities.MDbVersionInfo
import com.example.moduledb.controlDB.data.local.entities.MacroRegionEntity
import com.example.moduledb.controlDB.data.local.entities.RegionEntity
import com.example.moduledb.controlDB.utils.Converters


/**
 * En este apartado realizaremos la automigracion cada que se agregue una nueva tabla a nuestra base de datos.
 */
@Database(
    entities = [MDbPOIs::class, MDbPORecharge::class, MDbVersionInfo::class, MDbMacroRegions::class, MDbListLines::class, BrandEntity::class,RegionEntity::class,MacroRegionEntity::class],
    version = 8,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3),
        AutoMigration(from = 3, to = 4),
        AutoMigration(from = 4, to = 5),
        AutoMigration(from = 5, to = 6),
        AutoMigration(from = 6, to = 7),
        AutoMigration(from = 7, to = 8),
    ]
)
@TypeConverters(Converters::class)


abstract class AppDataBase : RoomDatabase() {

    abstract fun pointsInterest(): MDbPOIsDao
    abstract fun pointsRecharge(): MDbPORechargeDao
    abstract fun versionTable(): MDbVersionInfoDao
    abstract fun macroRegions(): MDbMacroRegionsDao
    abstract fun listMacroRegions(): MDbListLinesDao
}


