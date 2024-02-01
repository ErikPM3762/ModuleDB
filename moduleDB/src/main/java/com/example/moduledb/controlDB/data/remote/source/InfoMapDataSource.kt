package com.example.moduledb.controlDB.data.remote.source

import com.example.moduledb.controlDB.data.remote.models.MDbPOIsResponse
import com.example.moduledb.controlDB.data.remote.models.MDbPORechargeResponse
import com.example.moduledb.controlDB.data.remote.models.MDbVTPointInterestResponse
import com.example.moduledb.controlDB.data.remote.models.MDbVTPointRechargeResponse
import com.example.moduledb.controlDB.data.remote.request.POIsRequest
import com.example.moduledb.controlDB.data.remote.request.RechargingPointsRequest
import com.example.moduledb.controlDB.data.remote.server.OracleServiceApi
import com.example.moduledb.controlDB.utils.NetResult
import com.example.moduledb.controlDB.utils.RequestDataBase
import com.example.moduledb.controlDB.utils.parse
import com.example.moduledb.controlDB.utils.toNetworkResult
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class InfoMapDataSource @Inject constructor(private val serviceApi: OracleServiceApi) :
    IInfoMapDataSource {

    override suspend fun getPointsInterest(): Flow<NetResult<ArrayList<MDbPOIsResponse>>> =
        flow {
            emit(serviceApi.getPOIs(POIsRequest()))
        }.catch { error ->
            emit(error.toNetworkResult())
        }
            .map { res -> res.parse {
                it.result!!.pointOfInterestList!! } }
            .flowOn(Dispatchers.IO)

    override suspend fun getPointsRecharge(): Flow<NetResult<ArrayList<MDbPORechargeResponse>>> =
        flow {
            emit(serviceApi.getRechargingPoints(RechargingPointsRequest()))
        }.catch { error ->
            emit(error.toNetworkResult())
        }
            .map { res -> res.parse {
                it.result!!.pointOfRechargeList!! } }
            .flowOn(Dispatchers.IO)

    override suspend fun getVersionTablePointInterest(): Flow<NetResult<MDbVTPointInterestResponse>> =
        flow {
            emit(serviceApi.getPOIsVersion(RequestDataBase.data))
        }.catch { error ->
            emit(error.toNetworkResult())
        }.map { res -> res.parse {it.result!!.parse() } }
            .flowOn(Dispatchers.IO)

    override suspend fun getVersionTablePointRecharge(): Flow<NetResult<MDbVTPointRechargeResponse>> =
        flow {
            emit(serviceApi.getRechargingPointsVersion(RequestDataBase.data))
        }.catch { error ->
            emit(error.toNetworkResult())
        }.map { res -> res.parse {it.result!!.parse() } }
            .flowOn(Dispatchers.IO)
}