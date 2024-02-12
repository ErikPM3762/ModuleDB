package com.example.moduledb.controlDB.data.remote.repository

import android.util.Log
import com.example.moduledb.controlDB.data.local.daos.MDbStopsDao
import com.example.moduledb.controlDB.data.local.entities.MDbListStops
import com.example.moduledb.controlDB.data.local.mapers.toLinesByMacroRegions
import com.example.moduledb.controlDB.data.local.mapers.toMacroRegionList
import com.example.moduledb.controlDB.data.local.mapers.toStop
import com.example.moduledb.controlDB.data.remote.models.MDBMacroRegions
import com.example.moduledb.controlDB.data.remote.models.MDBStops
import com.example.moduledb.controlDB.data.remote.response.lines.BusLine
import com.example.moduledb.controlDB.data.remote.source.ILinesDataSource
import com.example.moduledb.controlDB.data.remote.source.IStopsDataSource
import com.example.moduledb.controlDB.utils.NetResult
import com.example.moduledb.controlDB.utils.getGenericError
import com.example.moduledb.controlDB.utils.loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LinesRepository @Inject constructor(
    private val remoteDataSource: ILinesDataSource
) {


    suspend fun getDetailLineById(
        idLocalCompany: Int,
        idBusline: String,
        state: String
    ): Flow<NetResult<List<BusLine>>> = flow {
        remoteDataSource.getDetailLineById(idLocalCompany, idBusline, state)
            .loading()
            .map { result ->
                if (result is NetResult.Success) {
                    Log.e("LISTA", result.data.toString())
                }
                result

            }.loading().catch { error ->
                emit(NetResult.Error(getGenericError()))
            }
            .flowOn(Dispatchers.IO).collect { emit(it) }

    }
}
