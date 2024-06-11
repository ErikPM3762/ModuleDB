package com.example.moduledb.controlDB.domain.usecase.api_here

import com.example.moduledb.controlDB.data.remote.response.getSearchDirections.Item
import com.example.moduledb.controlDB.data.remote.source.IApiHereDataSource
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetDirectionsByApiHere @Inject constructor(
    private val dataSource: IApiHereDataSource
) {
    operator fun invoke(
        latitude: String,
        longitude: String,
        query: String,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<NetResult<List<Item>>> =
        dataSource.getDirections(latitude, longitude, query).flowOn(dispatcher)
}