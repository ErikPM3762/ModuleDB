package com.example.moduledb.controlDB.data.remote.source

import com.example.moduledb.controlDB.data.mapers.toMacroRegionList
import com.example.moduledb.controlDB.data.models.MDBMacroRegions
import com.example.moduledb.controlDB.data.remote.request.POIsRequest
import com.example.moduledb.controlDB.data.remote.response.macroRegions.MacroRegion
import com.example.moduledb.controlDB.data.remote.response.macroRegions.MacroRegionsResponse
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

class RegionsDataSource @Inject constructor(
    private val oracleServiceApi: OracleServiceApi) :
    IRegionsDataSource {


    override suspend fun getStateInfo(idLocalCompany: Int): Flow<NetResult<List<MDBMacroRegions>>> =
        flow {
            emit(oracleServiceApi.getStates(RequestDataBase.getRequestByIdCompany(idLocalCompany)))
        }.catch { error ->
            emit(error.toNetworkResult())
        }
            .map { res ->
                res.parse {
                    it.result!!.statesList!!
                }
            }
            .flowOn(Dispatchers.IO)
}