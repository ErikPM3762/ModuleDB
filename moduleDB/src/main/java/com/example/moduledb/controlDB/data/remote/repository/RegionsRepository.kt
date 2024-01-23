package com.example.moduledb.controlDB.data.remote.repository


import com.example.moduledb.controlDB.data.local.daos.MDbListLinesDao
import com.example.moduledb.controlDB.data.local.daos.MDbMacroRegionsDao
import com.example.moduledb.controlDB.data.local.entities.MDbListLines
import com.example.moduledb.controlDB.data.mapers.toListLines
import com.example.moduledb.controlDB.data.mapers.toMacroRegionList
import com.example.moduledb.controlDB.data.models.MDBMacroRegions
import com.example.moduledb.controlDB.data.remote.source.IRegionsDataSource
import com.example.moduledb.controlDB.utils.NetResult
import com.example.moduledb.controlDB.utils.getGenericError
import com.example.moduledb.controlDB.utils.loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RegionsRepository @Inject constructor(
    private val remoteDataSource: IRegionsDataSource,
    private val macroRegionsDao: MDbMacroRegionsDao,
    private val listLinesDao: MDbListLinesDao
) {


    suspend fun getRegions(idLocalCompany: Int): Flow<NetResult<List<MDBMacroRegions>>> =
        remoteDataSource.getStateInfo(idLocalCompany)
            .loading()
            .map { result ->
                if (result is NetResult.Success) {
                    val macroRegionsList = result.data.toMacroRegionList()
                    val existingRegions = macroRegionsDao.getExistingRegions(macroRegionsList.map { it.idMacroRegion })
                    val newRegions = macroRegionsList.filter { region ->
                        existingRegions.none { it.idMacroRegion == region.idMacroRegion }
                    }
                    macroRegionsDao.insertOrUpdate(newRegions)
                }
                result
            }
            .loading()
            .catch { error -> emit(NetResult.Error(getGenericError())) }
            .flowOn(Dispatchers.IO)

    suspend fun getListLines (idLocalCompany: Int, idMacroRegion: String): Flow<NetResult<List<MDbListLines>>> =
        remoteDataSource.getListLines(idLocalCompany, idMacroRegion).loading().map { result ->
            if (result is NetResult.Success) {
                listLinesDao.insertListIfNotExists(result.data.toListLines(idMacroRegion))
            }
            result
        }.loading().catch { error -> emit(NetResult.Error(getGenericError())) }
            .flowOn(Dispatchers.IO)
}