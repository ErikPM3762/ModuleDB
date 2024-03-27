package com.example.moduledb.controlDB.data.local.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.moduledb.controlDB.data.local.entities.MDbRouteEntity


@Dao
abstract class MDbRouteDao : BaseDao<MDbRouteEntity, Long>() {
    @Query("SELECT * FROM MDbRouteEntity WHERE idBusLine = :idBusLine")
    abstract fun getRoutesByIdBusLine(idBusLine: String): List<MDbRouteEntity>

    @Query("SELECT COUNT(*)>0 FROM MDbRouteEntity WHERE idBusLine = :idBusLine")
    abstract fun getIfLocalDataExist(idBusLine: String): Boolean

}










