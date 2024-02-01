package com.example.moduledb.controlDB.data.remote.source

import com.example.moduledb.controlDB.data.local.entities.MDbListLines
import com.example.moduledb.controlDB.data.local.entities.MDbLinesByRegion
import com.example.moduledb.controlDB.data.remote.models.MDBMacroRegions
import com.example.moduledb.controlDB.data.remote.models.MDBRegions
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


    /**
     * Metodo para obtener las MacroRegiones
     */
    override suspend fun getStateInfo(idLocalCompany: Int): Flow<NetResult<List<MDBMacroRegions>>> =
        flow {
            emit(oracleServiceApi.getMacroStates(RequestDataBase.getRequestByIdCompany(idLocalCompany)))
        }.catch { error ->
            emit(error.toNetworkResult())
        }
            .map { res ->
                res.parse {
                    it.result!!.statesList!!
                }
            }
            .flowOn(Dispatchers.IO)

    /**
     * Metodo para obtener la lista de lineas por MacroRegion
     */
    override suspend fun getLinesByMacroRegions(idLocalCompany: Int, idMacroRegion: String): Flow<NetResult<List<MDbListLines>>> =
        flow {
            emit(oracleServiceApi.getLinesByMacroRegion(RequestDataBase.getRequestByIdCompanyListLines(idLocalCompany, idMacroRegion)))
        }.catch { error ->
            emit(error.toNetworkResult())
        }
            .map { res ->
                res.parse {
                    it.result!!.busLine!!
                }
            }
            .flowOn(Dispatchers.IO)

    /**
     * Metodo para obtener las Regiones
     */
    override suspend fun getRegionsInfo(idLocalCompany: Int): Flow<NetResult<List<MDBRegions>>> =
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

    /**
     * Metodo para obtener la lista de lineas por Region
     */
    override suspend fun getLinesByRegions(idLocalCompany: Int, idMacroRegion: String): Flow<NetResult<List<MDbLinesByRegion>>> =
        flow {
            emit(oracleServiceApi.getLinesByRegion(RequestDataBase.getRequestByIdCompanyListLines(idLocalCompany, idMacroRegion)))
        }.catch { error ->
            emit(error.toNetworkResult())
        }
            .map { res ->
                res.parse {
                    it.result!!.busLine!!
                }
            }
            .flowOn(Dispatchers.IO)
}