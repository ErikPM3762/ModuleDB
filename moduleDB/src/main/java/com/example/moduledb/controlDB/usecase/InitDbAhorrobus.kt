package com.example.moduledb.controlDB.usecase

import com.example.moduledb.controlDB.data.repository.InitDbRepository

/**
 * Caso de uso para realizar el llenado de la base de datos
 */
class InitDbAhorrobus(
    private val initDbRepository: InitDbRepository
) {

    suspend fun initializeDatabase() = initDbRepository.getPointsInterest()
}