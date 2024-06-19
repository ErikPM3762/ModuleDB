package com.example.moduledb.controlDB.data.remote.source

import com.example.moduledb.controlDB.data.remote.response.getSearchDirections.Item
import com.example.moduledb.controlDB.utils.NetResult
import kotlinx.coroutines.flow.Flow

fun interface IApiHereDataSource {
    fun getDirections(
        latitude: String,
        longitude: String,
        query: String
    ): Flow<NetResult<List<Item>>>
}