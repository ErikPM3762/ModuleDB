package com.example.moduledb.controlDB.data.remote.source


import com.example.moduledb.controlDB.data.local.entities.MDbLinesDetail
import com.example.moduledb.controlDB.data.remote.server.AwsServiceApi
import com.example.moduledb.controlDB.data.remote.server.OracleServiceApi
import com.example.moduledb.controlDB.utils.AppId.AHORROBUS
import com.example.moduledb.controlDB.utils.AppId.BENIDORM
import com.example.moduledb.controlDB.utils.NetResult
import com.example.moduledb.controlDB.utils.RequestDataBase
import com.example.moduledb.controlDB.utils.getGenericError
import com.example.moduledb.controlDB.utils.loading
import com.example.moduledb.controlDB.utils.parse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LinesDataSource @Inject constructor(
    private val oracleServiceApi: OracleServiceApi,
    private val awsServiceApi: AwsServiceApi
) : ILinesDataSource {


    /**
     * Metodo para obtener el detalle de linea
     */

    override suspend fun getDetailLineById(
        idLocalCompany: Int,
        idBusline: String,
        state: String
    ): Flow<NetResult<List<MDbLinesDetail>>> =
        flow {
            val request =
                RequestDataBase.getRequestByIdCompanyDetailLine(idLocalCompany, idBusline, state)
            when (idLocalCompany) {
                AHORROBUS.idLocalCompany -> emit(oracleServiceApi.getDetailOfLine(request))
                else -> emit(awsServiceApi.getDetailOfLine(request))
            }
        }.map { res ->
            res.parse { it.result!!.busLine }
        }.loading().catch { error ->
            emit(NetResult.Error(getGenericError()))
        }.flowOn(Dispatchers.IO)

    override fun getRouteDetailByIds(
        idLocalCompany: Int,
        idBusline: String,
        idPath: String
    ): Flow<NetResult<List<MDbLinesDetail>>> = flow {
        when (idLocalCompany) {
            BENIDORM.idLocalCompany -> emit(
                awsServiceApi.getDetailOfLine(
                    RequestDataBase.getRequestByIdCompanyDetailRoute(
                        idLocalCompany,
                        idBusline,
                        idPath
                    )
                )
            )
        }
    }.map { res ->
        res.parse { parse ->
            parse.result!!.busLine.map { it.copy(pathIdBusLine = idPath) }
        }
    }.catch { error ->
        emit(NetResult.Error(getGenericError()))
    }
}
