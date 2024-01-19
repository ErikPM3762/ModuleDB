package com.example.moduledb.controlDB.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameTable
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.example.moduledb.controlDB.data.local.daos.MDbMacroRegionsDao
import com.example.moduledb.controlDB.data.local.daos.MDbPOIsDao
import com.example.moduledb.controlDB.data.local.daos.MDbPORechargeDao
import com.example.moduledb.controlDB.data.local.daos.MDbVersionInfoDao
import com.example.moduledb.controlDB.data.local.entities.MDbMacroRegions
import com.example.moduledb.controlDB.data.local.entities.MDbPOIs
import com.example.moduledb.controlDB.data.local.entities.MDbPORecharge
import com.example.moduledb.controlDB.data.local.entities.MDbVersionInfo

/**
 * En este apartado realizaremos la automigracion cada que se agregue una nueva tabla a nuestra base de datos.
 */
@Database(
    entities = [MDbPOIs::class, MDbPORecharge::class, MDbVersionInfo::class, MDbMacroRegions::class],
    version = 7,
    autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = AppDataBase.AutoMigration1to2::class),
        AutoMigration(from = 2, to = 3),
        AutoMigration(from = 3, to = 4),
        AutoMigration(from = 4, to = 5),
        AutoMigration(from = 5, to = 6),
        AutoMigration(from = 6, to = 7)
    ]
)


abstract class AppDataBase : RoomDatabase() {

    abstract fun pointsInterest(): MDbPOIsDao
    abstract fun pointsRecharge(): MDbPORechargeDao
    abstract fun versionTable(): MDbVersionInfoDao

    abstract fun macroRegions(): MDbMacroRegionsDao

    @RenameTable(fromTableName = "PointsInterest", toTableName = "MDbPOIs")
    class AutoMigration1to2 : AutoMigrationSpec
}


