package com.example.moduledb.controlDB.data.remote.repository


import android.util.Log
import com.example.moduledb.controlDB.data.local.daos.MDbMacroRegionsDao
import com.example.moduledb.controlDB.data.local.daos.MDbPOIsDao
import com.example.moduledb.controlDB.data.local.daos.MDbPORechargeDao
import com.example.moduledb.controlDB.data.mapers.toMacroRegionList
import com.example.moduledb.controlDB.data.mapers.toPointsInterestList
import com.example.moduledb.controlDB.data.mapers.toPointsRechargeList
import com.example.moduledb.controlDB.data.models.MDBMacroRegions
import com.example.moduledb.controlDB.data.models.MDbPOIsResponse
import com.example.moduledb.controlDB.data.models.MDbPORechargeResponse
import com.example.moduledb.controlDB.data.models.MDbVTPointInterestResponse
import com.example.moduledb.controlDB.data.models.MDbVTPointRechargeResponse
import com.example.moduledb.controlDB.data.remote.source.IInfoMapDataSource
import com.example.moduledb.controlDB.data.remote.source.IRegionsDataSource
import com.example.moduledb.controlDB.utils.NetResult
import com.example.moduledb.controlDB.utils.getGenericError
import com.example.moduledb.controlDB.utils.loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RegionsRepository @Inject constructor(
    private val remoteDataSource: IRegionsDataSource,
    private val macroRegionsDao: MDbMacroRegionsDao
) {


    suspend fun getRegions(idLocalCompany: Int): Flow<NetResult<List<MDBMacroRegions>>> =
        remoteDataSource.getStateInfo(idLocalCompany).loading().map { result ->
            if (result is NetResult.Success) {
                Log.e("TEST_REGIONS", result.data.toString())
                Log.e("TEST_REGIONS", result.data.toMacroRegionList().toString())
                macroRegionsDao.insertOrUpdate(result.data.toMacroRegionList())
            }
            result
        }.loading().catch { error -> emit(NetResult.Error(getGenericError())) }
            .flowOn(Dispatchers.IO)
}