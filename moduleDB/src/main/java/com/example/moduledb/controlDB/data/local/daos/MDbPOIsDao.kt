package com.example.moduledb.controlDB.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.moduledb.controlDB.data.local.entities.MDbListStops
import com.example.moduledb.controlDB.data.local.entities.MDbPOIs

@Dao
abstract class MDbPOIsDao : BaseDao<MDbPOIs, Long>() {

    @Query("SELECT * from MDbPOIs")
    abstract fun getPointsInterestData(): List<MDbPOIs>
}