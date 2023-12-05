package com.example.moduledb.controlDB.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moduledb.controlDB.data.daos.PointsInterestDao
import com.example.moduledb.controlDB.data.entities.PointsInterest

/**
 * En este apartado realizaremos la automigracion cada que se agregue una nueva tabla a nuestra base de datos.
 */
@Database(
    entities = [PointsInterest::class],
    version = 1
)


abstract class AppDataBase : RoomDatabase() {

    abstract fun pointsInterest(): PointsInterestDao
}