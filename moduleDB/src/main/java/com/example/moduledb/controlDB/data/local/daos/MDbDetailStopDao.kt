package com.example.moduledb.controlDB.data.local.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.moduledb.controlDB.data.local.entities.MDbDetailStops
import com.example.moduledb.controlDB.data.local.entities.MDbListStops
import com.example.moduledb.controlDB.data.local.entities.MDdRegions

@Dao
abstract class MDbDetailStopDao : BaseDao<MDbDetailStops, Long>() {

}