package com.example.moduledb.controlDB.data.local.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.moduledb.controlDB.data.local.entities.MDbLinesByRegion
import com.example.moduledb.controlDB.data.local.entities.MDbLinesDetail


@Dao
abstract class MDbLinesDetailDao : BaseDao<MDbLinesDetail, Long>() {


    @Query("SELECT * FROM MDbLinesDetail WHERE idBusSAE = :idBusSAE")
    abstract fun findLineByIdBusSAE(idBusSAE: String): MDbLinesDetail?

}










