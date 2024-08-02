package com.example.moduledb.controlDB.data.remote.source

import com.example.moduledb.controlDB.data.remote.response.pointsInterest.GetPOIsAwsResponse
import com.example.moduledb.controlDB.data.remote.response.pointsInterest.POIsResponse
import com.example.moduledb.controlDB.data.remote.response.pointsRecharge.GetRPsAwsResponse
import com.example.moduledb.controlDB.data.remote.response.pointsRecharge.PORechargeResponse
import com.example.moduledb.controlDB.data.remote.server.AwsServiceApi
import com.example.moduledb.controlDB.data.remote.server.OracleServiceApi
import com.example.moduledb.controlDB.domain.models.MDbPOIsResponse
import com.example.moduledb.controlDB.domain.models.MDbPORechargeResponse
import com.example.moduledb.controlDB.domain.models.MDbVTPointInterestResponse
import com.example.moduledb.controlDB.domain.models.MDbVTPointRechargeResponse
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

class InfoMapDataSource @Inject constructor(
    private val oracleServiceApi: OracleServiceApi, private val awsServiceApi: AwsServiceApi
) : IInfoMapDataSource {

    override suspend fun getPointsInterest(idLocalCompany: Int): Flow<NetResult<ArrayList<MDbPOIsResponse>>> =
        flow {
            val request = RequestDataBase.getPOIRequesByIdCompany(idLocalCompany)
            val response: Response<POIsResponse> = oracleServiceApi.getPOIs(request)
            emit(response)

        }.catch { error ->
            emit(error.toNetworkResult())
        }.map { res ->
            res.parse {
                when (it) {
                    is POIsResponse -> it.result!!.pointOfInterestList!!
                    else -> throw Exception("Unknow option for getPointsInterest")
                }

            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getPointsRecharge(idLocalCompany: Int): Flow<NetResult<ArrayList<MDbPORechargeResponse>>> =
        flow {
            val request = RequestDataBase.getRPRequesByIdCompany(idLocalCompany)
            val response: Response<PORechargeResponse> =
                oracleServiceApi.getRechargingPoints(request)
            emit(response)

        }.catch { error ->
            emit(error.toNetworkResult())
        }.map { res ->
            res.parse {
                when (it) {
                    is PORechargeResponse -> it.result!!.pointOfRechargeList!!
                    else -> throw Exception("Unknow option for getPointsInterest")
                }
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getVersionTablePointInterest(): Flow<NetResult<MDbVTPointInterestResponse>> =
        flow {
            emit(oracleServiceApi.getPOIsVersion(RequestDataBase.data))
        }.catch { error ->
            emit(error.toNetworkResult())
        }.map { res -> res.parse { it.result!!.parse() } }.flowOn(Dispatchers.IO)

    override suspend fun getVersionTablePointRecharge(): Flow<NetResult<MDbVTPointRechargeResponse>> =
        flow {
            emit(oracleServiceApi.getRechargingPointsVersion(RequestDataBase.data))
        }.catch { error ->
            emit(error.toNetworkResult())
        }.map { res -> res.parse { it.result!!.parse() } }.flowOn(Dispatchers.IO)
}