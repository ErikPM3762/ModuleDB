package com.example.moduledb.controlDB.data.remote.repository

import com.example.moduledb.controlDB.data.local.daos.MDbLinesDetailDao
import com.example.moduledb.controlDB.data.local.entities.MDbLinesDetail
import com.example.moduledb.controlDB.data.local.entities.MDbListStops
import com.example.moduledb.controlDB.data.local.mapers.toDetailLineList
import com.example.moduledb.controlDB.data.remote.source.ILinesDataSource
import com.example.moduledb.controlDB.utils.NetResult
import com.example.moduledb.controlDB.utils.getGenericError
import com.example.moduledb.controlDB.utils.loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LinesRepository @Inject constructor(
    private val remoteDataSource: ILinesDataSource,
    private val linesDetailDao: MDbLinesDetailDao,
    private val linesDetailByIdDao: MDbLinesDetailDao
) {


    suspend fun getDetailLine(
        idLocalCompany: Int,
        idBusline: String,
        state: String
    ): Flow<NetResult<List<MDbLinesDetail>>> = flow {
        val localStops = withContext(Dispatchers.IO) {
            linesDetailByIdDao.findAll()
        }
        if (localStops.isNotEmpty()) {
            emit(NetResult.Success(localStops))
        } else {
            remoteDataSource.getDetailLineById(idLocalCompany, idBusline, state)
                .loading()
                .map { result ->
                    if (result is NetResult.Success) {
                        linesDetailDao.insertOrUpdate(result.data.toDetailLineList())
                    }
                    result

                }.loading().catch { error ->
                    emit(NetResult.Error(getGenericError()))
                }
                .flowOn(Dispatchers.IO).collect { emit(it) }

        }
    }

    suspend fun getDetailLineById(idBusSae: String) : MDbLinesDetail? {
        return withContext(Dispatchers.IO) {linesDetailByIdDao.findLineByIdBusSAE(idBusSae)}
    }
}
