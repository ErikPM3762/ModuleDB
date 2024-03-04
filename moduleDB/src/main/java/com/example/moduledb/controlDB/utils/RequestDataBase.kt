@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.example.moduledb.controlDB.utils

import com.example.moduledb.controlDB.data.remote.request.DetailLineAwseRequest
import com.example.moduledb.controlDB.data.remote.request.DetailLineRequest
import com.example.moduledb.controlDB.data.remote.request.DetailStopRequest
import com.example.moduledb.controlDB.data.remote.request.LinesListAwsRequest
import com.example.moduledb.controlDB.data.remote.request.LinesListRequest
import com.example.moduledb.controlDB.data.remote.request.MacroRegionsRequest
import com.example.moduledb.controlDB.data.remote.request.RoutesByIdLineRequest
import com.example.moduledb.controlDB.data.remote.request.StopsRequest
import com.example.moduledb.controlDB.data.remote.request.StopsSpainRequest
import com.example.moduledb.controlDB.data.remote.request.TeoricByTypeStopSegoviaRequest
import com.google.gson.JsonObject

object RequestDataBase {

    /**
     * Verificar si podemos cambiar a data class
     */
    val data: JsonObject = JsonObject().apply {
        addProperty("idFront", 51)
        addProperty("country", "mexico")
        addProperty("state", "")
        addProperty("cityOrTown", "")
        addProperty("idLocalCompany", "11")
    }

    fun getRequestByIdCompany(idLocalCompany: Int) = when (idLocalCompany) {
        AppId.BENIDORM.idLocalCompany -> {
            getBenidormRequest()
        }

        AppId.AHORROBUS.idLocalCompany -> getAhorrobusRequest()
        else -> throw IllegalArgumentException("Unknown id company for regions request")
    }

    fun getRequestByIdCompanyListLines(idLocalCompany: Int, idMacroRegion: String) =
        when (idLocalCompany) {
            AppId.BENIDORM.idLocalCompany -> getBenidormListLinesRequest(idMacroRegion)
            AppId.AHORROBUS.idLocalCompany -> getAhorrobusListLinesRequest(idMacroRegion)
            AppId.RUBI.idLocalCompany -> getVigoListLinesRequest(idLocalCompany.toString())
            else -> throw IllegalArgumentException("Unknown id company for regions request")
        }

    fun getRequestByIdCompanyStops(idLocalCompany: Int) = when (idLocalCompany) {
        AppId.BENIDORM.idLocalCompany -> getAhorrobusStopsRequest()
        AppId.AHORROBUS.idLocalCompany -> getAhorrobusStopsRequest()
        AppId.OURENSE.idLocalCompany -> getOurenseStopsRequest()
        else -> throw IllegalArgumentException("Unknown id company for regions request")
    }

    fun getRequestByIdCompanyDetailLine(idLocalCompany: Int, idBusLine: String, state: String) =
        when (idLocalCompany) {
            AppId.BENIDORM.idLocalCompany -> getBenidormDetailLineRequest(idBusLine)
            AppId.AHORROBUS.idLocalCompany -> getAhorrobusDetailLineRequest(idBusLine, state)
            AppId.RUBI.idLocalCompany -> getAwsDetailLineRequest(idBusLine, idLocalCompany)
            else -> throw IllegalArgumentException("Unknown id company for regions request")
        }

    fun getRequestByIdCompanyDetailStop(idLocalCompany: Int, idBusStop: String) =
        when (idLocalCompany) {
            AppId.BENIDORM.idLocalCompany -> getDetailStopAwsRequest(idBusStop)
            AppId.AHORROBUS.idLocalCompany -> getDetailStopOracleRequest(idBusStop)
            AppId.RUBI.idLocalCompany -> getDetailStopAwsRequest(idBusStop)
            else -> throw IllegalArgumentException("Unknown id company for regions request")
        }

    fun getRequestByIdCompanyDetailRoute(idLocalCompany: Int, idBusLine: String, idPath: String) =
        when (idLocalCompany) {
            AppId.BENIDORM.idLocalCompany -> getBenidormDetailRouteRequest(idBusLine, idPath)
            else -> throw IllegalArgumentException("Unknown id company for regions request")
        }

    private fun getAhorrobusRequest() = MacroRegionsRequest(
        idLocalCompany = "11",
        country = "mexico",
        cityOrTown = "mexico",
        state = "mexico",
        idFront = 51
    )

    private fun getBenidormRequest() = MacroRegionsRequest(
        country = "spain",
        cityOrTown = "benidorm",
        state = "benidorm",
        idLocalCompany = "5",
        idFront = 100
    )

    private fun getAhorrobusListLinesRequest(idMacroRegion: String) = LinesListRequest(
        idFront = 70,
        country = "intermedio",
        state = "intermedio",
        cityOrTown = "intermedio",
        idLocalCompany = "11",
        idMacroRegion = idMacroRegion,
        idRegion = "",
        idBrand = ""
    )

    private fun getVigoListLinesRequest(idLocalCompany: String) = LinesListAwsRequest(
        idFront = 100,
        country = "espana",
        state = "provincia_segovia",
        cityOrTown = "rubi",
        idLocalCompany = idLocalCompany
    )

    private fun getBenidormListLinesRequest(idRegion: String?) = LinesListRequest(
        idFront = 70,
        country = "spain",
        state = "benidorm",
        cityOrTown = "benidorm",
        idLocalCompany = "5",
        idMacroRegion = "",
        idRegion = idRegion ?: "",
        idBrand = ""
    )

    private fun getAhorrobusStopsRequest() = StopsRequest(
        idLocalCompany = "11",
        country = "mexico",
        cityOrTown = "mexico",
        state = "mexico",
        idFront = 51,
        idbusLine = "",
        idBrand = ""
    )

    private fun getOurenseStopsRequest() = StopsSpainRequest(
        idFront = 100,
        country = "españa",
        state = "ourense",
        cityOrTown = "ourense",
        idLocalCompany = "53"
    )

    private fun getDetailStopOracleRequest(idBusStop: String) = DetailStopRequest(
        idFront = 100,
        country = "intermedio",
        state = "intermedio",
        cityOrTown = "intermedio",
        idLocalCompany = "11",
        idBusLine = "",
        idBusStop = idBusStop
    )

    private fun getDetailStopAwsRequest(idBusStop: String) = DetailStopRequest(
        idFront = 100,
        country = "españa",
        state = "provincia",
        cityOrTown = "segovia",
        idLocalCompany = "5",
        idBusLine = "",
        idBusStop = idBusStop
    )

    fun getRequestByIdCompanyAws(idLocalCompany: Int, idBusLine: String, tripCode: String) =
        when (idLocalCompany) {
            AppId.SEGOVIA.idLocalCompany -> getSegoviaTeoricsByTypeStop(idBusLine, tripCode)
            else -> throw IllegalArgumentException("Unknown id company for regions request")
        }

    private fun getSegoviaTeoricsByTypeStop(idBusLine: String, tripCode: String) =
        TeoricByTypeStopSegoviaRequest(
            idFront = 100,
            country = "españa",
            state = "provincia_segovia",
            cityOrTown = "segovia",
            idLocalCompany = "65",
            idBusLine = idBusLine,
            tripCode = tripCode
        )

    fun getRoutesByIdRequestForBenidorm(idBusLine: String, tripCode: String = "I") =
        RoutesByIdLineRequest(
            idFront = 60,
            country = "spain",
            state = "benidorm",
            cityOrTown = "benidorm",
            idLocalCompany = "5",
            idBusLine = idBusLine,
            tripCode = tripCode
        )

    /**
     * Request para obtener el detalle de linea
     */
    private fun getBenidormDetailLineRequest(idBusLine: String) = DetailLineRequest(
        cityOrTown = "benidorm",
        country = "spain",
        idBusLine = idBusLine,
        idFront = 60,
        idLocalCompany = "5",
        state = "benidorm",
        pathIdBusLine = ""
    )

    private fun getAhorrobusDetailLineRequest(idBusLine: String, state: String) = DetailLineRequest(
        cityOrTown = "mexico",
        country = "mexico",
        idBusLine = idBusLine,
        idFront = 60,
        idLocalCompany = "11",
        state = state,
        pathIdBusLine = ""
    )

    private fun getAwsDetailLineRequest(idBusLine: String, idLocalCompany: Int) =
        DetailLineAwseRequest(
            cityOrTown = "segovia",
            country = "españa",
            idbusLine = idBusLine,
            idFront = 100,
            idLocalCompany = idLocalCompany.toString(),
            state = "provincia_segovia",
            62418
        )

    /**
     * Request para obtener el detalle de ruta
     */

    private fun getBenidormDetailRouteRequest(idBusLine: String, idPath: String) =
        DetailLineRequest(
            cityOrTown = "benidorm",
            country = "spain",
            idBusLine = idBusLine,
            idFront = 60,
            idLocalCompany = "5",
            state = "benidorm",
            pathIdBusLine = idPath
        )
}