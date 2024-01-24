package com.example.moduledb.controlDB.data.local.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.moduledb.controlDB.data.local.entities.MDbLinesByRegion


@Dao
abstract class MDbLinesByRegionDao : BaseDao<MDbLinesByRegion, Long>() {


    @Query("SELECT * FROM MDbLinesByRegion WHERE idMacroRegion = :idMacroRegion")
    abstract fun getMDbListLinesById(idMacroRegion: String): List<MDbLinesByRegion>

    @Query("DELETE FROM MDbLinesByRegion")
    abstract suspend fun deleteAll()

    @Query("SELECT * FROM MDbLinesByRegion")
    abstract fun getAllMDbListLines(): List<MDbLinesByRegion>
}










