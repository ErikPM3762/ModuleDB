package com.example.moduledb.controlDB.data.remote.source

import com.example.moduledb.controlDB.data.remote.response.getSearchDirections.Item
import com.example.moduledb.controlDB.data.remote.server.IApiHereServiceApi
import com.example.moduledb.controlDB.utils.NetResult
import com.example.moduledb.controlDB.utils.getGenericError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ApiHereDataSource
@Inject constructor(private val apiHereService: IApiHereServiceApi) : IApiHereDataSource {

    override fun getDirections(
        latitude: String,
        longitude: String,
        query: String
    ): Flow<NetResult<List<Item>>> = flow {
        val apiKey = "Tp6som6KTHqpseMCdhCsdXFvEPz4QLX48aN_hN968-s"
        val latLong = "$latitude,$longitude"
        val responde = apiHereService.getSearchDirections(latLong, query, apiKey)
        if (responde.isSuccessful && responde.body() != null)
            emit(NetResult.Success(responde.body()!!.items))
        else
            emit(NetResult.Error(getGenericError()))
    }.catch { error ->
        error.printStackTrace()
        emit(NetResult.Error(getGenericError()))
    }.flowOn(Dispatchers.IO)
}