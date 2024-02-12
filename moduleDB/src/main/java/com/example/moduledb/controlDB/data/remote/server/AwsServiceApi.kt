package com.example.moduledb.controlDB.data.remote.server

import com.example.moduledb.controlDB.data.remote.request.AuthTokenAwsRequest
import com.example.moduledb.controlDB.data.remote.request.DetailLinesListRequest
import com.example.moduledb.controlDB.data.remote.request.LinesListAwsRequest
import com.example.moduledb.controlDB.data.remote.request.LinesListRequest
import com.example.moduledb.controlDB.data.remote.request.MacroRegionsRequest
import com.example.moduledb.controlDB.data.remote.request.POIsRequest
import com.example.moduledb.controlDB.data.remote.request.RechargingPointsRequest
import com.example.moduledb.controlDB.data.remote.request.StopsRequest
import com.example.moduledb.controlDB.data.remote.request.StopsSpainRequest
import com.example.moduledb.controlDB.data.remote.request.TeoricByTypeStopSegoviaRequest
import com.example.moduledb.controlDB.data.remote.response.AuthTokenAwsResponse
import com.example.moduledb.controlDB.data.remote.response.AuthTokenResponse
import com.example.moduledb.controlDB.data.remote.response.lines.DetailLineResponse
import com.example.moduledb.controlDB.data.remote.response.lines.LinesByMacroRegionsResponse
import com.example.moduledb.controlDB.data.remote.response.lines.LinesByRegionsResponse
import com.example.moduledb.controlDB.data.remote.response.macroRegions.MacroRegionsResponse
import com.example.moduledb.controlDB.data.remote.response.pointsInterest.POIsResponse
import com.example.moduledb.controlDB.data.remote.response.pointsRecharge.PORechargeResponse
import com.example.moduledb.controlDB.data.remote.response.regions.RegionsResponse
import com.example.moduledb.controlDB.data.remote.response.stops.StopsResponse
import com.example.moduledb.controlDB.data.remote.response.teroicByStop.TeoricsByTypeStopResponse
import com.example.moduledb.controlDB.data.remote.response.versionTablePointInterest.VTPointInterestResponse
import com.example.moduledb.controlDB.data.remote.response.versionTablePointRecharge.VTPointRechargeResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
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

    @POST("ObtenerLista")
    suspend fun getLines(@Body params: LinesListAwsRequest): Response<LinesByRegionsResponse>

    @POST("ObtenerDetalleLinea")
    suspend fun getDetailOfLine(@Body params: Any): Response<DetailLineResponse>




}