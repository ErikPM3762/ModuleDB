package com.example.moduledb.controlDB.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moduledb.controlDB.data.daos.MDbPOIsDao
import com.example.moduledb.controlDB.data.daos.MDbPORechargeDao
import com.example.moduledb.controlDB.data.entities.MDbPOIs
import com.example.moduledb.controlDB.data.entities.MDbPORecharge

/**
 * En este apartado realizaremos la automigracion cada que se agregue una nueva tabla a nuestra base de datos.
 */
@Database(
    entities = [MDbPOIs::class, MDbPORecharge::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)


abstract class AppDataBase : RoomDatabase() {

    abstract fun pointsInterest(): MDbPOIsDao
    abstract fun pointsRecharge(): MDbPORechargeDao
}