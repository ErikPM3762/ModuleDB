package com.example.moduledb.controlDB.data.local.daos


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moduledb.controlDB.data.local.entities.MDbListStops
import com.example.moduledb.controlDB.data.local.entities.MDbVersionInfo

@Dao
abstract class MDbVersionInfoDao : BaseDao<MDbVersionInfo, Long>() {

    @Query("UPDATE MDbVersionInfo SET pointInterestVersion = :pointInterestValue WHERE id = 1")
    abstract suspend fun updatePointInterest(pointInterestValue: Long)

    @Query("UPDATE MDbVersionInfo SET pointRechargeVersion = :pointRechargeValue WHERE id = 1")
    abstract suspend fun updatePointRecharge(pointRechargeValue: Long)

    @Query("SELECT pointInterestVersion FROM MDbVersionInfo")
    abstract fun getPointInterestVersion(): Long

    @Query("SELECT pointRechargeVersion FROM MDbVersionInfo")
    abstract fun getPointRechargeVersion(): Long


    @Query("SELECT * FROM MDbVersionInfo WHERE id = 1")
    abstract suspend fun getRowWithIdOne(): MDbVersionInfo?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertOrUpdateDefaultRow(versionInfo: MDbVersionInfo): Long

    suspend fun insertOrUpdatePointInterestAndRecharge(pointInterestValue: Long, pointRechargeValue: Long) {
        val existingRow = getRowWithIdOne()

        if (existingRow == null) {
            insertOrUpdateDefaultRow(MDbVersionInfo(id = 1, pointInterestVersion = pointInterestValue, pointRechargeVersion = pointRechargeValue))
        } else {
            existingRow.pointInterestVersion = pointInterestValue
            existingRow.pointRechargeVersion = pointRechargeValue
            insertOrUpdateDefaultRow(existingRow)
        }
    }
}
