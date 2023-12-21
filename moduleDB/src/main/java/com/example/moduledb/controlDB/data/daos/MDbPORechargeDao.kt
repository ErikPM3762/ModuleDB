package com.example.moduledb.controlDB.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.moduledb.controlDB.data.entities.MDbPORecharge

@Dao
abstract class MDbPORechargeDao : BaseDao <MDbPORecharge,Long>() {

    @Query("SELECT * from MDbPORecharge")
    abstract fun getPointsRechargeData(): LiveData<MDbPORecharge>
}