package com.example.moduledb.controlDB.data.local.daos

import android.util.Log
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.moduledb.controlDB.data.local.entities.BusStopEntity
import com.example.moduledb.controlDB.data.local.entities.MDbListStops
import com.example.moduledb.controlDB.data.local.entities.MDbListTheoricByTypeStop
import com.example.moduledb.controlDB.data.local.entities.MDdRegions

@Dao
abstract class MDbTheoricsByTypeStopDao : BaseDao<MDbListTheoricByTypeStop, Long>() {

    @Query("SELECT * FROM MDbListTheoricByTypeStop WHERE idLineGenerate = :idLineGenerate")
    abstract fun getByLineGenerate(idLineGenerate: String): MDbListTheoricByTypeStop?

    @Query("SELECT * FROM MDbListTheoricByTypeStop WHERE idLineGenerate = :idLineGenerate AND tripCode = :tripCode")
    abstract fun getMDbListByLine(idLineGenerate: String, tripCode: String): MDbListTheoricByTypeStop

    @Transaction
    @Query("SELECT * FROM MDbListTheoricByTypeStop WHERE idLineGenerate = :idLineGenerate AND tripCode = :tripCode")
    suspend fun getBusStopById(idBusStop: String, idLineGenerate: String, tripCode: String): BusStopEntity? {
        val mdbList = getMDbListByLine(idLineGenerate, tripCode)
        if (mdbList == null || mdbList.busStop.isNullOrEmpty()) {
            return null
        } else {
            val busStops = mdbList.busStop
            for (busStopEntity in busStops) {
                if (busStopEntity.idBusStop == idBusStop) {
                    return busStopEntity
                }
            }
            return null
        }
    }
}