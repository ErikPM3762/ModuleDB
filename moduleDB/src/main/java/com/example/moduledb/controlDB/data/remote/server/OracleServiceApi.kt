package com.example.moduledb.controlDB.data.remote.server

import com.example.moduledb.controlDB.data.remote.request.BaseRequest
import com.example.moduledb.controlDB.data.remote.request.LinesListRequest
import com.example.moduledb.controlDB.data.remote.request.MacroRegionsRequest
import com.example.moduledb.controlDB.data.remote.request.StopsRequest
import com.example.moduledb.controlDB.data.remote.response.AuthTokenResponse
import com.example.moduledb.controlDB.data.remote.response.lines.DetailLineResponse
import com.example.moduledb.controlDB.data.remote.response.lines.LinesByMacroRegionsResponse
import com.example.moduledb.controlDB.data.remote.response.lines.LinesByRegionsResponse
import com.example.moduledb.controlDB.data.remote.response.macroRegions.MacroRegionsResponse
import com.example.moduledb.controlDB.data.remote.response.pointsInterest.POIsResponse
import com.example.moduledb.controlDB.data.remote.response.pointsRecharge.PORechargeResponse
import com.example.moduledb.controlDB.data.remote.response.regions.RegionsResponse
import com.example.moduledb.controlDB.data.remote.response.stops.DetailStopsResponse
import com.example.moduledb.controlDB.data.remote.response.stops.StopsResponse
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

    /**
     * Apis para hacer el llamado de los datos para Ahorrobus y replicas Mexico Oracle
     */
    @POST("/apis/1.1.0/puntosDeInteres/1.0.0/obtenerListaPuntosDeInteres")
    suspend fun getPOIs(@Body params: BaseRequest): Response<POIsResponse>

    @POST("/apis/1.1.0/tarjetasPrepago/1.0.0/obtenerListaCentrosDeRecarga")
    suspend fun getRechargingPoints(@Body params: BaseRequest): Response<PORechargeResponse>

    @POST("/apis/1.1.0/puntosDeInteres/1.0.0/obtenerListaPuntosDeInteres/version")
    suspend fun getPOIsVersion(@Body params: JsonObject): Response<VTPointInterestResponse>

    @POST("/apis/1.1.0/tarjetasPrepago/1.0.0/obtenerListaCentrosDeRecarga/version")
    suspend fun getRechargingPointsVersion(@Body params: JsonObject): Response<VTPointRechargeResponse>

    @POST("/apis/1.1.0/lineasYParadas/1.0.0/obtenerListaMacroRegiones")
    suspend fun getMacroStates(@Body params: MacroRegionsRequest): Response<MacroRegionsResponse>

    @POST("/apis/1.1.0/lineasYParadas/1.1.0/obtenerListaLineas")
    suspend fun getLinesByMacroRegion(@Body params: LinesListRequest): Response<LinesByMacroRegionsResponse>

    @POST("/apis/1.1.0/lineasYParadas/1.1.0/obtenerDetalleLinea")
    suspend fun getDetailOfLine(@Body params: Any): Response<DetailLineResponse>

    @POST("apis/1.1.0/lineasYParadas/1.1.0/obtenerDetalleParada")
    suspend fun getDetailStopsById(@Body params: Any): Response<DetailStopsResponse>

    @POST("/apis/1.1.0/lineasYParadas/1.1.0/obtenerListaParadas")
    suspend fun getStops(@Body params: StopsRequest): Response<StopsResponse>


    @FormUrlEncoded
    @POST("/apis/1.0.0/usuarios/1.0.0/invitado")
    fun getAuthToken(
        @Field("grant_type") grantType: String,
        @Field("scope") ADOAPIs: String
    ): Call<AuthTokenResponse>


    /**
     * Apis para hacer el llamado de los datos para Benidorm y replicas España Oracle
     */
    @POST("/apis/1.1.0/lineasYParadas/1.0.0/obtenerListaRegiones")
    suspend fun getStates(@Body params: MacroRegionsRequest): Response<RegionsResponse>

    @POST("/apis/1.1.0/lineasYParadas/1.1.0/obtenerListaLineas")
    suspend fun getLinesByRegion(@Body params: LinesListRequest): Response<LinesByRegionsResponse>

}