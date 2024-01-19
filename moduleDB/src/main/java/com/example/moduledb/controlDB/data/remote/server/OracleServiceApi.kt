package com.example.moduledb.controlDB.data.remote.server

import com.example.moduledb.controlDB.data.remote.request.DetailLinesListRequest
import com.example.moduledb.controlDB.data.remote.request.LinesListRequest
import com.example.moduledb.controlDB.data.remote.request.MacroRegionsRequest
import com.example.moduledb.controlDB.data.remote.request.POIsRequest
import com.example.moduledb.controlDB.data.remote.request.RechargingPointsRequest
import com.example.moduledb.controlDB.data.remote.response.AuthTokenResponse
import com.example.moduledb.controlDB.data.remote.response.lines.DetailLineResponse
import com.example.moduledb.controlDB.data.remote.response.lines.LinesListResponse
import com.example.moduledb.controlDB.data.remote.response.macroRegions.MacroRegionsResponse
import com.example.moduledb.controlDB.data.remote.response.pointsInterest.POIsResponse
import com.example.moduledb.controlDB.data.remote.response.pointsRecharge.PORechargeResponse
import com.example.moduledb.controlDB.data.remote.response.versionTablePointInterest.VTPointInterestResponse
import com.example.moduledb.controlDB.data.remote.response.versionTablePointRecharge.VTPointRechargeResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface OracleServiceApi {

    @POST("/apis/1.1.0/puntosDeInteres/1.0.0/obtenerListaPuntosDeInteres")
    suspend fun getPOIs(@Body params: POIsRequest): Response<POIsResponse>

    @POST("/apis/1.1.0/tarjetasPrepago/1.0.0/obtenerListaCentrosDeRecarga")
    suspend fun getRechargingPoints(@Body params: RechargingPointsRequest): Response<PORechargeResponse>

    @POST("/apis/1.1.0/puntosDeInteres/1.0.0/obtenerListaPuntosDeInteres/version")
    suspend fun getPOIsVersion(@Body params: JsonObject): Response<VTPointInterestResponse>

    @POST("/apis/1.1.0/tarjetasPrepago/1.0.0/obtenerListaCentrosDeRecarga/version")
    suspend fun getRechargingPointsVersion(@Body params: JsonObject): Response<VTPointRechargeResponse>

    @POST("/apis/1.1.0/lineasYParadas/1.0.0/obtenerListaMacroRegiones")
    suspend fun getStates(@Body params: MacroRegionsRequest): Response<MacroRegionsResponse>

    @POST("/apis/1.1.0/lineasYParadas/1.1.0/obtenerListaLineas")
    suspend fun getListaLineas(@Body params: LinesListRequest): Response<LinesListResponse>

    @POST("/apis/1.1.0/lineasYParadas/1.1.0/obtenerDetalleLinea")
    suspend fun getDetailOfLine(@Body params: DetailLinesListRequest): Response<DetailLineResponse>


    @FormUrlEncoded
    @POST("/apis/1.0.0/usuarios/1.0.0/invitado")
    fun getAuthToken(
        @Field("grant_type") grantType: String,
        @Field("scope") ADOAPIs: String
    ): Call<AuthTokenResponse>
}