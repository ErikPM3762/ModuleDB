package com.example.moduledb.controlDB.data.remote.repository

import android.util.Log
import com.example.moduledb.controlDB.data.DataResult
import com.example.moduledb.controlDB.data.local.daos.MDbDetailStopDao
import com.example.moduledb.controlDB.data.local.daos.MDbStopsDao
import com.example.moduledb.controlDB.data.local.entities.MDbListStops
import com.example.moduledb.controlDB.data.local.mapers.toDetailStop
import com.example.moduledb.controlDB.data.local.mapers.toRoomDetailStop
import com.example.moduledb.controlDB.data.local.mapers.toStop
import com.example.moduledb.controlDB.data.performUpdateOperation
import com.example.moduledb.controlDB.data.remote.server.AwsServiceApi
import com.example.moduledb.controlDB.data.remote.server.OracleServiceApi
import com.example.moduledb.controlDB.data.remote.source.IStopsDataSource
import com.example.moduledb.controlDB.domain.models.MDBDetailStop
import com.example.moduledb.controlDB.domain.repository.StopsRepository
import com.example.moduledb.controlDB.utils.NetResult
import com.example.moduledb.controlDB.utils.RequestDataBase
import com.example.moduledb.controlDB.utils.getGenericError
import com.example.moduledb.controlDB.utils.loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StopsRepositoryImpl @Inject constructor(
    private val oracleServiceApi: OracleServiceApi,
    private val awsServiceApi: AwsServiceApi,
    private val remoteDataSource: IStopsDataSource,
    private val stopsDao: MDbStopsDao,
    private val detailStopsDao: MDbDetailStopDao
) : StopsRepository {

    /**
     * funcion get para obtener los teoricos por parada AWS
     *//*   suspend fun getMacroRegions(idLocalCompany: Int): Flow<NetResult<List<MDBMacroRegions>>> =
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
        val localStops = withContext(Dispatchers.IO) {
            stopsDao!!.getAllStops()
        }
        if (localStops.isNotEmpty()) {
            emit(NetResult.Success(localStops))
        } else {
            remoteDataSource!!.getStops(idLocalCompany).loading().map { result ->
                if (result is NetResult.Success) {
                    val stopList = result.data.toStop()
                    stopsDao!!.insertOrUpdate(stopList)
                }
                result
            }.loading().catch { error -> emit(NetResult.Error(getGenericError())) }
                .flowOn(Dispatchers.IO).collect { emit(it) }
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getStopsByBuslineCrossingId(buslineCrossingId: String): List<MDbListStops> {
        val allStops = withContext(Dispatchers.IO) { stopsDao!!.getAllStops() }
        val stopsWithBuslineCrossingId = mutableListOf<MDbListStops>()

        allStops.forEach { stop ->
            if (stop.buslineCrossing?.contains(buslineCrossingId) == true) {
                stopsWithBuslineCrossingId.add(stop)
            }
        }

        return stopsWithBuslineCrossingId
    }

    suspend fun getStopById(idBusStop: Int): MDbListStops? {
        return withContext(Dispatchers.IO) { stopsDao!!.getStopById(idBusStop) }
    }

    override suspend fun getDetailStopsById(
        idLocalCompany: Int, idBusStop: String
    ): DataResult<MDBDetailStop, Exception> = withContext(Dispatchers.IO) {
        val localDetailStop = withContext(Dispatchers.IO) {detailStopsDao.findById(idBusStop.toLong())}
        Log.e("respondeme", "ww")
        if (localDetailStop != null) {
            Log.e("respondeme", "pp")
            val dataConverter = localDetailStop.toDetailStop()
            return@withContext DataResult.Success(dataConverter)
        }
        else {
            performUpdateOperation({
                oracleServiceApi.getDetailStopsById(
                    RequestDataBase.getRequestByIdCompanyDetailStop(
                        idLocalCompany, idBusStop
                    )
                )
            }, { response ->
                Log.e("respondeme", response.toString())
                response?.result?.stopsList?.toRoomDetailStop()
            }, { detailStop ->
                Log.e("respondeme 2", detailStop.toString())
                detailStopsDao.insertOrUpdate(detailStop)
                detailStop.toDetailStop()
            })
        }
    }
}