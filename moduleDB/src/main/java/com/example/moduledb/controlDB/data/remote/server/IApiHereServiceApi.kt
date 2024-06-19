package com.example.moduledb.controlDB.data.remote.server

import com.example.moduledb.controlDB.data.remote.response.getSearchDirections.GetSearchDirectionsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

fun interface IApiHereServiceApi {
    @GET("discover")
    suspend fun getSearchDirections(
        @Query("at") latLong: String,
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): Response<GetSearchDirectionsResponse>
}