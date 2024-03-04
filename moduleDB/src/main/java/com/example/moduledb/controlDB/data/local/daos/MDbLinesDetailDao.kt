package com.example.moduledb.controlDB.data.local.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.moduledb.controlDB.data.local.entities.MDbLinesDetail


@Dao
abstract class MDbLinesDetailDao : BaseDao<MDbLinesDetail, Long>() {

    @Query("SELECT * FROM MDbLinesDetail WHERE idBusSAE =:idBusSAE AND pathIdBusLine IS NULL")
    abstract fun findLineByIdBusSAE(idBusSAE: String): MDbLinesDetail?

    @Query("SELECT * FROM MDbLinesDetail WHERE idBusSAE =:idBusSAE AND pathIdBusLine =:pathIdBusLine")
    abstract fun findLineByIds(idBusSAE: String, pathIdBusLine: String): MDbLinesDetail?

}










