package com.example.moduledb.controlDB.data.remote.server

import com.example.moduledb.controlDB.data.remote.request.AuthTokenAwsRequest
import com.example.moduledb.controlDB.data.remote.request.BaseRequest
import com.example.moduledb.controlDB.data.remote.request.LinesListAwsRequest
import com.example.moduledb.controlDB.data.remote.request.RoutesByIdLineRequest
import com.example.moduledb.controlDB.data.remote.request.StopsSpainRequest
import com.example.moduledb.controlDB.data.remote.request.TeoricByTypeStopRequest
import com.example.moduledb.controlDB.data.remote.response.AuthTokenAwsResponse
import com.example.moduledb.controlDB.data.remote.response.lines.DetailLineResponse
import com.example.moduledb.controlDB.data.remote.response.lines.LinesByRegionsResponse
import com.example.moduledb.controlDB.data.remote.response.pointsInterest.GetPOIsAwsResponse
import com.example.moduledb.controlDB.data.remote.response.pointsRecharge.GetRPsAwsResponse
import com.example.moduledb.controlDB.data.remote.response.routes.RoutesByIdLineResponse
import com.example.moduledb.controlDB.data.remote.response.stops.DetailStopsResponse
import com.example.moduledb.controlDB.data.remote.response.stops.StopsResponse
import com.example.moduledb.controlDB.data.remote.response.stops.map.GetMapStopsAwsResponse
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
    suspend fun getTeoricByTypeStop(@Body params: TeoricByTypeStopRequest): Response<TeoricsByTypeStopResponse>

    @POST("ObtenerListaParadas")
    suspend fun getStops(@Body params: StopsSpainRequest): Response<StopsResponse>

    @POST("Signin")
    fun getAWSAuthToken(@Body params: AuthTokenAwsRequest): Call<AuthTokenAwsResponse>

    @POST("ObtenerLista")
    suspend fun getLines(@Body params: LinesListAwsRequest): Response<LinesByRegionsResponse>

    @POST("ObtenerDetalleLinea")
    suspend fun getDetailOfLine(@Body params: Any): Response<DetailLineResponse>

    @POST("ObtenerTrayectos")
    suspend fun getRoutesByIdLine(@Body params: RoutesByIdLineRequest): Response<RoutesByIdLineResponse>

    @POST("ObtenerDetalleParada")
    suspend fun getDetailStopsById(@Body params: Any): Response<DetailStopsResponse>

    @POST("ListarPuntosInteres")
    suspend fun getPointsOfInterest(@Body params: BaseRequest): Response<GetPOIsAwsResponse>

    @POST("ListarCentrosRecarga")
    suspend fun getRechargingPoints(@Body params: BaseRequest): Response<GetRPsAwsResponse>
    @POST("ObtenerMapaParadas")
    suspend fun getMapStops(@Body params: BaseRequest): Response<GetMapStopsAwsResponse>
}