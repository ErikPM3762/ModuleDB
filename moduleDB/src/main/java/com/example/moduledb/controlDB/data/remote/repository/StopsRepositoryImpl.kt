package com.example.moduledb.controlDB.data.remote.repository

import android.util.Log
import com.example.moduledb.controlDB.data.DataResult
import com.example.moduledb.controlDB.data.local.daos.MDbDetailStopDao
import com.example.moduledb.controlDB.data.local.daos.MDbStopsDao
import com.example.moduledb.controlDB.data.local.daos.MDbTheoricsByTypeStopDao
import com.example.moduledb.controlDB.data.local.entities.BusStopEntity
import com.example.moduledb.controlDB.data.local.entities.MDbListStops
import com.example.moduledb.controlDB.data.local.mapers.toDetailStop
import com.example.moduledb.controlDB.data.local.mapers.toRoomDetailStop
import com.example.moduledb.controlDB.data.local.mapers.toRoomTheoricByTypeStop
import com.example.moduledb.controlDB.data.local.mapers.toStop
import com.example.moduledb.controlDB.data.local.mapers.toTheoricByTypeStop
import com.example.moduledb.controlDB.data.performUpdateOperation
import com.example.moduledb.controlDB.data.remote.server.AwsServiceApi
import com.example.moduledb.controlDB.data.remote.server.OracleServiceApi
import com.example.moduledb.controlDB.data.remote.source.IStopsDataSource
import com.example.moduledb.controlDB.domain.models.MDBDetailStop
import com.example.moduledb.controlDB.domain.models.MDBTheoricByTypeStop
import com.example.moduledb.controlDB.domain.repository.StopsRepository
import com.example.moduledb.controlDB.utils.AppId
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
    private val detailStopsDao: MDbDetailStopDao,
    private val theoricByTypeStop: MDbTheoricsByTypeStopDao,
) : StopsRepository {

    /**
     * funcion get para obtener los teoricos por parada AWS
     */
    suspend fun getTheoricByTypeStopImpl(idLocalCompany: Int, idBusLine: String, tripCode: String): Flow<NetResult<Any>> = flow {
        val localTheoricTypeStop = withContext(Dispatchers.IO) {
            theoricByTypeStop.getByLineGenerate(idBusLine)
        }
        if (localTheoricTypeStop?.idLineGenerate == idBusLine) {
            emit(NetResult.Success(localTheoricTypeStop.toTheoricByTypeStop()))
        } else {
            remoteDataSource.getTeoricByTypeStop(idLocalCompany, idBusLine, tripCode).loading().map { result ->
                    if (result is NetResult.Success) {
                        val theorics = result.data.toRoomTheoricByTypeStop(idBusLine, tripCode)
                        theoricByTypeStop.insertOrUpdate(theorics)
                    }
                    result
                }
                .loading().catch { error ->
                    emit(NetResult.Error(getGenericError())) }
                .flowOn(Dispatchers.IO).collect{emit(it)}
        }
    } .flowOn(Dispatchers.IO)

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

    suspend fun getTheoricStopById(idBusStop: String, idLineGenerate: String, tripCode: String): BusStopEntity? {
        return withContext(Dispatchers.IO) { theoricByTypeStop!!.getBusStopById(idBusStop, idLineGenerate, tripCode) }
    }

    override suspend fun getDetailStopsById(
        idLocalCompany: Int, idBusStop: String
    ): DataResult<MDBDetailStop, Exception> = withContext(Dispatchers.IO) {
        val localDetailStop =
            withContext(Dispatchers.IO) { detailStopsDao.findById(idBusStop.toLong()) }
        if (localDetailStop != null) {
            val dataConverter = localDetailStop.toDetailStop()
            return@withContext DataResult.Success(dataConverter)
        } else {
            when (idLocalCompany) {
                AppId.AHORROBUS.idLocalCompany -> {
                    performUpdateOperation({
                        oracleServiceApi.getDetailStopsById(
                            RequestDataBase.getRequestByIdCompanyDetailStop(
                                idLocalCompany, idBusStop
                            )
                        )
                    }, { response ->
                        response?.result?.stopsList?.toRoomDetailStop()
                    }, { detailStop ->
                        detailStopsDao.insertOrUpdate(detailStop)
                        detailStop.toDetailStop()
                    })
                }

                else -> {
                    performUpdateOperation({
                        awsServiceApi.getDetailStopsById(
                            RequestDataBase.getRequestByIdCompanyDetailStop(
                                idLocalCompany, idBusStop
                            )
                        )
                    }, { response ->
                        response?.result?.stopsList?.toRoomDetailStop()
                    }, { detailStop ->
                        detailStopsDao.insertOrUpdate(detailStop)
                        detailStop.toDetailStop()
                    })
                }
            }
        }
    }
}