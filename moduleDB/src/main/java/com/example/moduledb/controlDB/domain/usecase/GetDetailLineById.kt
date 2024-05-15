package com.example.moduledb.controlDB.domain.usecase

import com.example.moduledb.controlDB.data.local.entities.MDbLinesDetail
import com.example.moduledb.controlDB.data.remote.repository.LinesRepository
import javax.inject.Inject

class GetDetailLineById @Inject constructor(private val repository: LinesRepository) {

    suspend fun invoke(idBusSae: String) : MDbLinesDetail? =
        repository.getDetailLineById(idBusSae)
}