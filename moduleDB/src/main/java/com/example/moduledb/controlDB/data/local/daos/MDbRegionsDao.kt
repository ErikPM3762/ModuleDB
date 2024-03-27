package com.example.moduledb.controlDB.data.local.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.moduledb.controlDB.data.local.entities.MDdRegions

@Dao
abstract class MDbRegionsDao : BaseDao<MDdRegions, Long>() {

    @Query("SELECT * from MDdRegions")
    abstract fun getRegions(): List<MDdRegions>

    @Query("SELECT * FROM MDdRegions WHERE idMacroRegion IN (:regionIds)")
    abstract fun getExistingRegions(regionIds: List<String>): List<MDdRegions>
}