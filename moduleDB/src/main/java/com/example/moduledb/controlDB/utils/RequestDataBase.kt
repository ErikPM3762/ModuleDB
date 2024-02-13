@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.example.moduledb.controlDB.utils

import com.example.moduledb.controlDB.data.remote.request.DetailLineAwseRequest
import com.example.moduledb.controlDB.data.remote.request.DetailLineRequest
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

    fun getRoutesByIdRequest(
        idFront: Int = 100,
        country: String = "ourense",
        state: String = "ourense",
        cityOrTown: String = "ourense",
        idLocalCompany: String = "53",
        idBusLine: String = "1",
        tripCode: String = "I"
    ) = RoutesByIdLineRequest(
        idFront, country, state, cityOrTown, idLocalCompany, idBusLine, tripCode
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
    )

    private fun getAhorrobusDetailLineRequest(idBusLine: String, state: String) = DetailLineRequest(
        cityOrTown = "mexico",
        country = "mexico",
        idBusLine = idBusLine,
        idFront = 60,
        idLocalCompany = "11",
        state = state,
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
}