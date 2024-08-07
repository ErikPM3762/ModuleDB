package com.example.moduledb.controlDB.data.remote.source

import com.example.moduledb.controlDB.data.local.entities.MDbListStops
import com.example.moduledb.controlDB.data.local.mapers.toStop
import com.example.moduledb.controlDB.data.remote.request.StopsRequest
import com.example.moduledb.controlDB.data.remote.request.StopsSpainRequest
import com.example.moduledb.controlDB.data.remote.response.stops.StopsResponse
import com.example.moduledb.controlDB.data.remote.server.AwsServiceApi
import com.example.moduledb.controlDB.data.remote.server.OracleServiceApi
import com.example.moduledb.controlDB.domain.models.MDBTheoricByTypeStop
import com.example.moduledb.controlDB.utils.AppId
import com.example.moduledb.controlDB.utils.NetResult
import com.example.moduledb.controlDB.utils.RequestDataBase
import com.example.moduledb.controlDB.utils.parse
import com.example.moduledb.controlDB.utils.toNetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject

class StopDataSource @Inject constructor(
    private val oracleServiceApi: OracleServiceApi,
    private val awsServiceApi: AwsServiceApi
) : IStopsDataSource {

    /**
     * Metodo para obtener teoricos por tipo parada
     */
    override suspend fun getTeoricByTypeStop(
        idLocalCompany: Int,
        idBusLine: String,
        tripCode: String
    ): Flow<NetResult<MDBTheoricByTypeStop>> =
        flow {
            emit(
                awsServiceApi.getTeoricByTypeStop(
                    RequestDataBase.getRequestByTypeStops(
                        idLocalCompany,
                        idBusLine,
                        tripCode
                    )
                )
            )
        }.catch { error -> emit(error.toNetworkResult()) }
            .map { res ->
                res.parse {
                    it.result.timeTableDaybusStop
                }
            }
            .flowOn(Dispatchers.IO)


    /**
     * Metodo para obtener la lista de todas las paradas
     * Ahorrobus
     */
    override suspend fun getStops(idLocalCompany: Int): Flow<NetResult<List<MDbListStops>>> = flow {
        when (idLocalCompany) {
            AppId.AHORROBUS.idLocalCompany -> {
                val request =
                    RequestDataBase.getRequestByIdCompanyStops(idLocalCompany) as StopsRequest
                val response: Response<StopsResponse> = oracleServiceApi.getStops(request)
                emit(response)
            }
            else -> {
                val request =
                    RequestDataBase.getRequestByIdCompanyStops(idLocalCompany) as StopsSpainRequest
                val response: Response<StopsResponse> = awsServiceApi.getStops(request)
                emit(response)
            }
        }

    }.catch { error ->
        emit(error.toNetworkResult())
    }
        .map { res ->
            res.parse { response ->
                response.result?.stopsList!!.toStop()
            }
        }
        .flowOn(Dispatchers.IO)

    override fun getMapStops(idLocalCompany: Int): Flow<NetResult<List<MDbListStops>>> = flow {
        val request = RequestDataBase.getMapStopsRequestByIdCompany(idLocalCompany)
        val response = awsServiceApi.getMapStops(request)
        emit(response)
    }.catch { error ->
        emit(error.toNetworkResult())
    }.map { res ->
        res.parse { response ->
            response.result.features.map { feature ->
                MDbListStops(
                    idBusStop = feature.properties.idBusStop.toIntOrNull() ?: 0,
                    desBusStop = feature.properties.popupContent,
                    coordinates = feature.geometry.coordinates.map { it.toString() },
                    buslineCrossing = emptyList(),
                    brands = emptyList()
                )
            }
        }
    }
}