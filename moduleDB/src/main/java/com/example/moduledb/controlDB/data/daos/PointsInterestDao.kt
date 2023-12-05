package com.example.moduledb.controlDB.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.moduledb.controlDB.data.entities.PointsInterest

@Dao
abstract class PointsInterestDao : BaseDao <PointsInterest,Long>() {

    @Query("SELECT * from PointsInterest")
    abstract fun getPointsInterestData(): LiveData<PointsInterest>
}