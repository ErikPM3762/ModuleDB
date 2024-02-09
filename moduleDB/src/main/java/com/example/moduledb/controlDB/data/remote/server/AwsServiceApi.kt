package com.example.moduledb.controlDB.data.remote.server

import com.example.moduledb.controlDB.data.remote.request.AuthTokenAwsRequest
import com.example.moduledb.controlDB.data.remote.request.RoutesByIdLineRequest
import com.example.moduledb.controlDB.data.remote.request.StopsSpainRequest
import com.example.moduledb.controlDB.data.remote.request.TeoricByTypeStopSegoviaRequest
import com.example.moduledb.controlDB.data.remote.response.AuthTokenAwsResponse
import com.example.moduledb.controlDB.data.remote.response.stops.StopsResponse
import com.example.moduledb.controlDB.data.remote.response.teroicByStop.TeoricsByTypeStopResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AwsServiceApi {

    /**
     * Apis para hacer el llamado de los datos para replicas de AWS
     * Segovia
     */

    @POST("ObtenerTeoricoPorTipoParada")
    suspend fun getTeoricByTypeStop(@Body params: TeoricByTypeStopSegoviaRequest): Response<TeoricsByTypeStopResponse>

    @POST("ObtenerListaParadas")
    suspend fun getStops(@Body params: StopsSpainRequest): Response<StopsResponse>

    @POST("Signin")
    fun getAWSAuthToken(@Body params: AuthTokenAwsRequest): Call<AuthTokenAwsResponse>


    @POST("ObtenerTrayectos")
    suspend fun getRoutesByIdLine(@Body params: RoutesByIdLineRequest): Response<Body>

}