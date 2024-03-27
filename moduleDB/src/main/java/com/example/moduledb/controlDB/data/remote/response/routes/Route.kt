package com.example.moduledb.controlDB.data.remote.response.routes

import com.example.moduledb.controlDB.data.local.entities.MDbRouteEntity

data class Route(
    val direction: String,
    val pathIdBusLine: String,
    val pathIdDescription: String
)

fun Route.toRouteEntity(idBusLine: String): MDbRouteEntity = with(this) {
    MDbRouteEntity(
        idBusLine = idBusLine,
        pathIdBusLine = pathIdBusLine,
        pathIdDescription = pathIdDescription,
        direction = direction
    )
}
