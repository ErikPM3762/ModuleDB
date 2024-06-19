package com.example.moduledb.controlDB.data.remote.repository

import com.example.moduledb.controlDB.data.local.daos.MDbLinesDetailDao
import com.example.moduledb.controlDB.data.local.entities.MDbLinesDetail
import com.example.moduledb.controlDB.data.local.mapers.toDetailLineList
import com.example.moduledb.controlDB.data.remote.source.ILinesDataSource
import com.example.moduledb.controlDB.utils.NetResult
import com.example.moduledb.controlDB.utils.getGenericError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LinesRepository @Inject constructor(
    private val remoteDataSource: ILinesDataSource,
    private val linesDetailDao: MDbLinesDetailDao,
    private val linesDetailByIdDao: MDbLinesDetailDao
) {


    fun getDetailLine(
        idLocalCompany: Int,
        idBusline: String,
        state: String
    ): Flow<NetResult<List<MDbLinesDetail>>> = flow {
        val localData = linesDetailDao.findLineByIdBusSAE(idBusline)
        when (localData == null) {
            true -> getRemoteLineDetail(idLocalCompany, idBusline, state).collect { emit(it) }
            false -> emit(NetResult.Success(listOf(localData)))
        }
    }

    private suspend fun getRemoteLineDetail(idLocalCompany: Int, idBusline: String, state: String) =
        remoteDataSource.getDetailLineById(idLocalCompany, idBusline, state)
            .map { result ->
                if (result is NetResult.Success) {
                    val data = result.data.toDetailLineList()
                    linesDetailDao.insertOrUpdate(data)
                }
                result
            }.catch {
                emit(NetResult.Error(getGenericError()))
            }


    suspend fun getDetailLineById(idBusSae: String): MDbLinesDetail? {
        return withContext(Dispatchers.IO) { linesDetailByIdDao.findLineByIdBusSAE(idBusSae) }
    }

    fun getRouteDetail(
        idLocalCompany: Int,
        idBusline: String,
        pathIdBusLine: String,
    ): Flow<NetResult<List<MDbLinesDetail>>> = flow {
        val localData = linesDetailDao.findLineByIds(idBusline, pathIdBusLine)
        when (localData == null) {
            true -> getRemoteRouteDetail(
                idLocalCompany,
                idBusline,
                pathIdBusLine
            ).collect { emit(it) }

            false -> emit(NetResult.Success(listOf(localData)))
        }
    }

    private suspend fun getRemoteRouteDetail(
        idLocalCompany: Int,
        idBusline: String,
        pathIdBusLine: String
    ) =
        remoteDataSource.getRouteDetailByIds(idLocalCompany, idBusline, pathIdBusLine)
            .map { result ->
                if (result is NetResult.Success)
                    linesDetailDao.insertOrUpdate(result.data.toDetailLineList())
                result
            }.catch {
                emit(NetResult.Error(getGenericError()))
            }

}
