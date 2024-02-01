package com.example.moduledb.controlDB.data.remote.repository

import com.example.moduledb.controlDB.data.local.daos.MDbStopsDao
import com.example.moduledb.controlDB.data.local.entities.MDbListStops
import com.example.moduledb.controlDB.data.local.mapers.toMacroRegionList
import com.example.moduledb.controlDB.data.local.mapers.toStop
import com.example.moduledb.controlDB.data.remote.models.MDBMacroRegions
import com.example.moduledb.controlDB.data.remote.models.MDBStops
import com.example.moduledb.controlDB.data.remote.source.IStopsDataSource
import com.example.moduledb.controlDB.utils.NetResult
import com.example.moduledb.controlDB.utils.getGenericError
import com.example.moduledb.controlDB.utils.loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StopsRepository @Inject constructor(
    private val remoteDataSource: IStopsDataSource,
    private val stopsDao : MDbStopsDao
) {

    /**
     * funcion get para obtener los teoricos por parada AWS
     */
    /*   suspend fun getMacroRegions(idLocalCompany: Int): Flow<NetResult<List<MDBMacroRegions>>> =
         remoteDataSource.getTeoricByTypeStop(idLocalCompany)
             .loading()
             .map { result ->
                 if (result is NetResult.Success) {
                     val macroRegionsList = result.data.toMacroRegionList()
                     val existingRegions = macroRegionsDao.getExistingMacroRegions(macroRegionsList.map { it.idMacroRegion })
                     val newMacroRegions = macroRegionsList.filter { region ->
                         existingRegions.none { it.idMacroRegion == region.idMacroRegion }
                     }
                     macroRegionsDao.insertOrUpdate(newMacroRegions)
                 }
                 result
             }
             .loading()
             .catch { error -> emit(NetResult.Error(getGenericError())) }
             .flowOn(Dispatchers.IO)*/

    suspend fun getStopsOracle(idLocalCompany: Int): Flow<NetResult<List<Any>>> = flow {
        val localStops = stopsDao.getAllStops()
        if (localStops.isNotEmpty()) {
            emit(NetResult.Success(localStops))
        } else {
            remoteDataSource.getStops(idLocalCompany)
                .loading()
                .map { result ->
                    if (result is NetResult.Success) {
                        val stopList = result.data.toStop()
                        stopsDao.insertOrUpdate(stopList)
                    }
                    result
                }
                .loading()
                .catch { error ->
                    emit(NetResult.Error(getGenericError()))
                }
                .flowOn(Dispatchers.IO)
                .collect { emit(it) }
        }
    }.flowOn(Dispatchers.IO)
}