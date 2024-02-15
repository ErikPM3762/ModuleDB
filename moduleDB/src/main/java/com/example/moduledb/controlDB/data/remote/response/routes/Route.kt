package com.example.moduledb.controlDB.data.remote.response.routes

import com.example.moduledb.controlDB.data.local.entities.MDbRouteEntity

data class Route(
    val direction: Int,
    val pathIdBusLine: Int,
    val pathIdDescription: String
)

fun Route.toRouteEntity(idBusLine: String): MDbRouteEntity = with(this) {
    MDbRouteEntity(
        idBusLine = idBusLine,
        pathIdBusLine = pathIdBusLine.toString(),
        pathIdDescription = pathIdDescription,
        direction = direction.toString()
    )
}
