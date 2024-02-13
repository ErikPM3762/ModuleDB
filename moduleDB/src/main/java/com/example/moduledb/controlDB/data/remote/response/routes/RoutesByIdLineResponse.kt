package com.example.moduledb.controlDB.data.remote.response.routes

import com.example.moduledb.controlDB.data.remote.response.HeaderResponse

data class RoutesByIdLineResponse(
    val header: HeaderResponse,
    val result: List<Route>
)

fun RoutesByIdLineResponse.toRouteEntity(idBusLine: String) =
    this.result.map { it.toRouteEntity(idBusLine) }

