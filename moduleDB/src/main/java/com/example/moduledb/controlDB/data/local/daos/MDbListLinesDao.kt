package com.example.moduledb.controlDB.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moduledb.controlDB.data.local.entities.MDbListLines


@Dao
abstract class MDbListLinesDao : BaseDao<MDbListLines, Long>() {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertListIfNotExists(mdbListLines: List<MDbListLines>)

    @Query("SELECT * FROM MDbListLines WHERE idMacroRegion = :idMacroRegion")
    abstract fun getMDbListLinesById(idMacroRegion: String): List<MDbListLines>


    @Query("DELETE FROM MDbListLines")
    abstract suspend fun deleteAll()

    @Query("SELECT * FROM MDbListLines")
    abstract fun getAllMDbListLines(): List<MDbListLines>

}








