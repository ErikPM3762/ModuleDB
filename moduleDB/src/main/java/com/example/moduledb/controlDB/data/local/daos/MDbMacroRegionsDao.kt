package com.example.moduledb.controlDB.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.moduledb.controlDB.data.local.entities.MDbMacroRegions
import com.example.moduledb.controlDB.data.local.entities.MDbPOIs

@Dao
abstract class MDbMacroRegionsDao : BaseDao<MDbMacroRegions, Long>() {

    @Query("SELECT * from MDbMacroRegions")
    abstract fun getMacrpoRegions(): List<MDbMacroRegions>

    @Query("SELECT * FROM MDBMacroRegions WHERE idMacroRegion IN (:regionIds)")
    abstract fun getExistingRegions(regionIds: List<String>): List<MDbMacroRegions>
}