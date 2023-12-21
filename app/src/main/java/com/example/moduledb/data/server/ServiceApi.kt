package com.example.moduledb.data.server

import com.example.moduledb.data.server.request.POIsRequest
import com.example.moduledb.data.server.request.RechargingPointsRequest
import com.example.moduledb.data.server.response.AuthTokenResponse
import com.example.moduledb.data.server.response.pointsInterest.POIsResponse
import com.example.moduledb.data.server.response.pointsRecharge.PORechargeResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ServiceApi {

    @POST("/apis/1.1.0/puntosDeInteres/1.0.0/obtenerListaPuntosDeInteres")
    suspend fun getPOIs(@Body params: POIsRequest): Response<POIsResponse>

    @POST("/apis/1.1.0/tarjetasPrepago/1.0.0/obtenerListaCentrosDeRecarga")
    suspend fun getRechargingPoints(@Body params: RechargingPointsRequest): Response<PORechargeResponse>

    @FormUrlEncoded
    @POST("/apis/1.0.0/usuarios/1.0.0/invitado")
    fun getAuthToken(
        @Field("grant_type") grantType: String,
        @Field("scope") ADOAPIs: String
    ): Call<AuthTokenResponse>
}