package com.example.moduledb.controlDB.data.remote.source

import com.example.moduledb.controlDB.domain.models.MDBStops
import com.example.moduledb.controlDB.domain.models.MDbPOIsResponse
import com.example.moduledb.controlDB.data.remote.request.StopsRequest
import com.example.moduledb.controlDB.data.remote.request.StopsSpainRequest
import com.example.moduledb.controlDB.data.remote.server.AwsServiceApi
import com.example.moduledb.controlDB.data.remote.server.OracleServiceApi
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
import javax.inject.Inject

class StopDataSource @Inject constructor(
    private val oracleServiceApi: OracleServiceApi,
    private val awsServiceApi: AwsServiceApi)
:
    IStopsDataSource {

    /**
     * Metodo para obtener teoricos por tipo parada
     */
    override suspend fun getTeoricByTypeStop(
        idLocalCompany: Int,
        idBusLine: String,
        tripCode: String
    ): Flow<NetResult<List<MDbPOIsResponse>>> {
        TODO("Not yet implemented")
    }


    /**
     * Metodo para obtener la lista de todas las paradas
     * Ahorrobus
     */
    override suspend fun getStops(idLocalCompany: Int): Flow<NetResult<List<MDBStops>>> =
        flow {
            if (idLocalCompany == 53) emit(awsServiceApi.getStops(RequestDataBase.getRequestByIdCompanyStops(idLocalCompany) as StopsSpainRequest))
            else emit(oracleServiceApi.getStops(RequestDataBase.getRequestByIdCompanyStops(idLocalCompany) as StopsRequest))
        }.catch { error ->
            emit(error.toNetworkResult())}
            .map { res ->
                res.parse {
                    it.result?.stopsList!!
                }
            }
            .flowOn(Dispatchers.IO)
}