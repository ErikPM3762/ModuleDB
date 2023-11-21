package com.example.moduledb.controlDB.data.repository

import androidx.lifecycle.LiveData
import com.example.moduledb.controlDB.data.AppDataBase
import com.example.moduledb.controlDB.data.models.PointsInterest

class InitDbRepository(
    private val appDatabase: AppDataBase
) {

    /**
     * Aqui vamos a inicializar la base de datos sin esperar datos de retorno
     */
  /*   suspend fun getPointsInterest(): LiveData<PointsInterest> = { result ->
        appDatabase.pointsInterest().insertOrUpdate(result)
    }*/



}