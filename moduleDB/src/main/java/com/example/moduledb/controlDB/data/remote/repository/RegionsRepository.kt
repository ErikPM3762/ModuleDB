package com.example.moduledb.controlDB.data.remote.repository


import android.util.Log
import com.example.moduledb.controlDB.data.local.daos.MDbLinesByMacroRegionDao
import com.example.moduledb.controlDB.data.local.daos.MDbLinesByRegionDao
import com.example.moduledb.controlDB.data.local.daos.MDbMacroRegionsDao
import com.example.moduledb.controlDB.data.local.daos.MDbRegionsDao
import com.example.moduledb.controlDB.data.local.entities.MDbListLines
import com.example.moduledb.controlDB.data.local.entities.MDbLinesByRegion
import com.example.moduledb.controlDB.data.local.entities.MDdRegions
import com.example.moduledb.controlDB.data.local.mapers.toLinesByMacroRegions
import com.example.moduledb.controlDB.data.local.mapers.toLinesByRegions
import com.example.moduledb.controlDB.data.local.mapers.toMacroRegionList
import com.example.moduledb.controlDB.data.local.mapers.toRegionList
import com.example.moduledb.controlDB.data.remote.models.MDBMacroRegions
import com.example.moduledb.controlDB.data.remote.models.MDBRegions
import com.example.moduledb.controlDB.data.remote.source.IRegionsDataSource
import com.example.moduledb.controlDB.utils.NetResult
import com.example.moduledb.controlDB.utils.getGenericError
import com.example.moduledb.controlDB.utils.loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegionsRepository @Inject constructor(
    private val remoteDataSource: IRegionsDataSource,
    private val macroRegionsDao: MDbMacroRegionsDao,
    private val regionsDao: MDbRegionsDao,
    private val linesByMacroRegionDao: MDbLinesByMacroRegionDao,
    private val linesByRegionDao: MDbLinesByRegionDao
) {

    /**
     * funcion get para obtener las macroRegiones
     * toMacroRegionList transforma el resultado para guardarse en la base de datos
     */
    suspend fun getMacroRegions(idLocalCompany: Int): Flow<NetResult<List<Any>>> = flow {
        val localMacroRegions = withContext(Dispatchers.IO) {
            macroRegionsDao.getMacroRegions()
        }
        if (localMacroRegions.isNotEmpty()) {
            emit(NetResult.Success(localMacroRegions))
        } else {
            remoteDataSource.getStateInfo(idLocalCompany).loading().map { result ->
                    if (result is NetResult.Success) {
                        val macroRegionsList = result.data.toMacroRegionList()
                        val existingRegions =
                            macroRegionsDao.getExistingMacroRegions(macroRegionsList.map { it.idMacroRegion })
                        val newMacroRegions = macroRegionsList.filter { region ->
                            existingRegions.none { it.idMacroRegion == region.idMacroRegion }
                        }
                        macroRegionsDao.insertOrUpdate(newMacroRegions)
                    }
                    result
                }.loading().catch { error ->
                    emit(NetResult.Error(getGenericError()))
                }.flowOn(Dispatchers.IO).collect { emit(it) }
        }
    }.flowOn(Dispatchers.IO)

    /**
     * funcion get para obtener las lineas por MacroRegion
     * toLinesByMacroRegions transforma el resultado para guardarse en la base de datos
     */
    suspend fun getLinesByMacroRegion(
        idLocalCompany: Int, idMacroRegion: String
    ): Flow<NetResult<List<Any>>> = flow {
        val localMacroRegions = withContext(Dispatchers.IO) {
            linesByMacroRegionDao.getMDbListLinesById(idMacroRegion)
        }
        if (localMacroRegions.isNotEmpty()) {
            emit(NetResult.Success(localMacroRegions))
        } else {
            remoteDataSource.getLinesByMacroRegions(idLocalCompany, idMacroRegion).loading()
                .map { result ->
                    if (result is NetResult.Success) {
                        linesByMacroRegionDao.insertListIfNotExists(
                            result.data.toLinesByMacroRegions(
                                idMacroRegion
                            )
                        )
                    }
                    result
                }.loading().catch { error ->
                    emit(NetResult.Error(getGenericError()))
                }.flowOn(Dispatchers.IO).collect { emit(it) }
        }
    }.flowOn(Dispatchers.IO)

    /**
     * funcion get para obtener las Regiones de Benidorm
     * toRegionList transforma el resultado para guardarse en la base de datos
     */
    suspend fun getRegions(idLocalCompany: Int): Flow<NetResult<List<Any>>> = flow {
        val localRegions = withContext(Dispatchers.IO) {
            regionsDao.getRegions()
        }
        if (localRegions.isNotEmpty()) {
            emit(NetResult.Success(localRegions))
        } else {
            remoteDataSource.getRegionsInfo(idLocalCompany).loading().map { result ->
                    if (result is NetResult.Success) {
                        val regionsList = result.data.toRegionList()
                        val existingRegions =
                            regionsDao.getExistingRegions(regionsList.map { it.idMacroRegion })
                        val newRegions = regionsList.filter { region ->
                            existingRegions.none { it.idMacroRegion == region.idMacroRegion }
                        }
                        regionsDao.insertOrUpdate(newRegions)
                    }
                    result
                }.loading().catch { error ->
                emit(NetResult.Error(getGenericError())) }
                .flowOn(Dispatchers.IO).collect { emit(it) }
        }
    }.flowOn(Dispatchers.IO)

    /**
     * funcion get para obtener las Lineas por Region de Benidorm
     * toLinesByRegions transforma el resultado para guardarse en la base de datos
     */
    suspend fun getLinesByRegion(
        idLocalCompany: Int, idMacroRegion: String
    ): Flow<NetResult<List<Any>>> = flow {
        val localLinesByRegion = withContext(Dispatchers.IO) {
            linesByRegionDao.getMDbListLinesById(idMacroRegion)
        }
        if (localLinesByRegion.isNotEmpty()) {
            emit(NetResult.Success(localLinesByRegion))
        } else {
            remoteDataSource.getLinesByRegions(idLocalCompany, idMacroRegion).loading()
                .map { result ->
                    if (result is NetResult.Success) {
                        linesByRegionDao.insertOrUpdate(result.data.toLinesByRegions(idMacroRegion))
                    }
                    result
                }.loading().catch { error -> emit(NetResult.Error(getGenericError())) }
                .flowOn(Dispatchers.IO).collect{emit(it)}
        }
    }
}