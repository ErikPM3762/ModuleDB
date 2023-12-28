package com.example.moduledb.controlDB.data.remote.source

import com.example.moduledb.controlDB.data.models.MDbPOIsResponse
import com.example.moduledb.controlDB.data.models.MDbPORechargeResponse
import com.example.moduledb.controlDB.data.models.MDbVTPointInterestResponse
import com.example.moduledb.controlDB.data.models.MDbVTPointRechargeResponse
import com.example.moduledb.controlDB.data.remote.ServiceApi
import com.example.moduledb.controlDB.data.remote.request.POIsRequest
import com.example.moduledb.controlDB.data.remote.request.RechargingPointsRequest
import com.example.moduledb.controlDB.data.remote.response.versionTablePointInterest.VTPointInterestResultResponse
import com.example.moduledb.controlDB.utils.NetResult
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

class RetrofitInfoMapDataSource @Inject constructor(private val serviceApi: ServiceApi) :
    InfoMapRemoteDataSource {

    val commonRequestData = JsonObject().apply {
        addProperty("idFront", 51)
        addProperty("country", "mexico")
        addProperty("state", "")
        addProperty("cityOrTown", "")
        addProperty("idLocalCompany", "11")
    }

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
            emit(serviceApi.getPOIsVersion(commonRequestData))
        }.catch { error ->
            emit(error.toNetworkResult())
        }.map { res -> res.parse {it.result!!.parse() } }
            .flowOn(Dispatchers.IO)

    override suspend fun getVersionTablePointRecharge(): Flow<NetResult<MDbVTPointRechargeResponse>> =
        flow {
            emit(serviceApi.getRechargingPointsVersion(commonRequestData))
        }.catch { error ->
            emit(error.toNetworkResult())
        }.map { res -> res.parse {it.result!!.parse() } }
            .flowOn(Dispatchers.IO)
}