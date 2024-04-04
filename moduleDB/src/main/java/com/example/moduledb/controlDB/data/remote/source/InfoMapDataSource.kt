package com.example.moduledb.controlDB.data.remote.source

import com.example.moduledb.controlDB.data.remote.request.RechargingPointsRequest
import com.example.moduledb.controlDB.data.remote.response.pointsInterest.GetPOIsAwsResponse
import com.example.moduledb.controlDB.data.remote.response.pointsInterest.POIsResponse
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
    private val oracleServiceApi: OracleServiceApi,
    private val awsServiceApi: AwsServiceApi
) : IInfoMapDataSource {

    override suspend fun getPointsInterest(idLocalCompany: Int): Flow<NetResult<ArrayList<MDbPOIsResponse>>> =
        flow {
            val request = RequestDataBase.getPOIRequesByIdCompany(idLocalCompany)
            when (idLocalCompany) {
                AppId.AHORROBUS.idLocalCompany -> {
                    val response: Response<POIsResponse> = oracleServiceApi.getPOIs(request)
                    emit(response)
                }

                AppId.BENIDORM.idLocalCompany,
                AppId.OURENSE.idLocalCompany -> {
                    val response: Response<GetPOIsAwsResponse> =
                        awsServiceApi.getPointsOfInterestByIdCompany(request)
                    emit(response)
                }

                else -> throw Exception("Unknow option for getPointsInterest")
            }
        }.catch { error ->
            emit(error.toNetworkResult())
        }.map { res ->
            res.parse {
                when (it) {
                    is POIsResponse -> it.result!!.pointOfInterestList!!
                    is GetPOIsAwsResponse -> {
                        val data = it.result.pointsOfInterest.map { poiAws ->
                            MDbPOIsResponse(
                                poiAws.id.toString(),
                                poiAws.head,
                                poiAws.description,
                                poiAws.address,
                                poiAws.phone,
                                poiAws.latitude,
                                poiAws.longitude
                            )
                        }
                        val arrayList = ArrayList<MDbPOIsResponse>(data.count())
                        arrayList.addAll(data)
                        arrayList
                    }

                    else -> throw Exception("Unknow option for getPointsInterest")
                }

            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getPointsRecharge(): Flow<NetResult<ArrayList<MDbPORechargeResponse>>> =
        flow {
            emit(oracleServiceApi.getRechargingPoints(RechargingPointsRequest()))
        }.catch { error ->
            emit(error.toNetworkResult())
        }
            .map { res ->
                res.parse {
                    it.result!!.pointOfRechargeList!!
                }
            }
            .flowOn(Dispatchers.IO)

    override suspend fun getVersionTablePointInterest(): Flow<NetResult<MDbVTPointInterestResponse>> =
        flow {
            emit(oracleServiceApi.getPOIsVersion(RequestDataBase.data))
        }.catch { error ->
            emit(error.toNetworkResult())
        }.map { res -> res.parse { it.result!!.parse() } }
            .flowOn(Dispatchers.IO)

    override suspend fun getVersionTablePointRecharge(): Flow<NetResult<MDbVTPointRechargeResponse>> =
        flow {
            emit(oracleServiceApi.getRechargingPointsVersion(RequestDataBase.data))
        }.catch { error ->
            emit(error.toNetworkResult())
        }.map { res -> res.parse { it.result!!.parse() } }
            .flowOn(Dispatchers.IO)
}