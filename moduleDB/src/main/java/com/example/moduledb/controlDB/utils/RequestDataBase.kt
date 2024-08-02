@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.example.moduledb.controlDB.utils

import com.example.moduledb.controlDB.data.remote.request.BaseRequest
import com.example.moduledb.controlDB.data.remote.request.DetailLineAwseRequest
import com.example.moduledb.controlDB.data.remote.request.DetailLineRequest
import com.example.moduledb.controlDB.data.remote.request.DetailStopRequest
import com.example.moduledb.controlDB.data.remote.request.LinesListAwsRequest
import com.example.moduledb.controlDB.data.remote.request.LinesListRequest
import com.example.moduledb.controlDB.data.remote.request.MacroRegionsRequest
import com.example.moduledb.controlDB.data.remote.request.RoutesByIdLineRequest
import com.example.moduledb.controlDB.data.remote.request.StopsRequest
import com.example.moduledb.controlDB.data.remote.request.StopsSpainRequest
import com.example.moduledb.controlDB.data.remote.request.TeoricByTypeStopRequest
import com.example.moduledb.controlDB.utils.AppId.AHORROBUS
import com.example.moduledb.controlDB.utils.AppId.BENIDORM
import com.example.moduledb.controlDB.utils.AppId.RUBI
import com.example.moduledb.controlDB.utils.AppId.VIGO
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
        BENIDORM.idLocalCompany -> {
            getBenidormRequest()
        }

        AHORROBUS.idLocalCompany -> getAhorrobusRequest()
        else -> throw IllegalArgumentException("Unknown id company for regions request")
    }

    fun getPOIRequesByIdCompany(idLocalCompany: Int): BaseRequest = when (idLocalCompany) {
        AppId.ARAGON.idLocalCompany, AppId.VIGO.idLocalCompany, AppId.RUBI.idLocalCompany, AppId.MATARO.idLocalCompany, AppId.SEGOVIA.idLocalCompany  -> BaseRequest(
            idFront = 100,
            country = "españa",
            state = "provincia_segovia",
            cityOrTown = "segovia",
            idLocalCompany = idLocalCompany.toString(),
            idLiferayCompany = "126451"
        )


        BENIDORM.idLocalCompany -> BaseRequest(
            idFront = 100,
            country = "spain",
            state = "benidorm",
            cityOrTown = "benidorm",
            idLocalCompany = idLocalCompany.toString()
        )

        AHORROBUS.idLocalCompany -> BaseRequest(
            idFront = 51,
            country = "mexico",
            state = "",
            cityOrTown = "",
            idLocalCompany = idLocalCompany.toString()
        )

        else -> BaseRequest(
            idFront = 100,
            country = "generic",
            state = "generic",
            cityOrTown = "generic",
            idLocalCompany = idLocalCompany.toString()
        )

    }

    fun getRPRequesByIdCompany(idLocalCompany: Int): BaseRequest = when (idLocalCompany) {
        AHORROBUS.idLocalCompany -> BaseRequest(
            idFront = 51,
            country = "mexico",
            state = "",
            cityOrTown = "",
            idLocalCompany = idLocalCompany.toString()
        )

        else -> BaseRequest(
            idFront = 100,
            country = "generic",
            state = "generic",
            cityOrTown = "generic",
            idLocalCompany = idLocalCompany.toString()
        )
    }

    fun getRequestByIdCompanyListLines(idLocalCompany: Int, idMacroRegion: String) =
        when (idLocalCompany) {
            BENIDORM.idLocalCompany -> getBenidormListLinesRequest(idMacroRegion)
            AHORROBUS.idLocalCompany -> getAhorrobusListLinesRequest(idMacroRegion)
            RUBI.idLocalCompany -> getVigoListLinesRequest(idLocalCompany.toString())
            else -> throw IllegalArgumentException("Unknown id company for regions request")
        }

    fun getAllLinesRequestByIdLocalCompany(idLocalCompany: Int): LinesListAwsRequest {
        val request = getAllLinesRequest(
            state = "generic",
            cityOrTown = "generic",
            idLocalCompany = idLocalCompany.toString()
        )
        return request
    }

    private fun getAllLinesRequest(
        country: String = "spain",
        state: String,
        cityOrTown: String,
        idLocalCompany: String
    ) = LinesListAwsRequest(
        country = country, state = state,
        cityOrTown = cityOrTown, idFront = 100,
        idLocalCompany = idLocalCompany
    )


    fun getRequestByIdCompanyStops(idLocalCompany: Int) = when (idLocalCompany) {
        AHORROBUS.idLocalCompany -> StopsRequest(
            idLocalCompany = "11",
            country = "mexico",
            cityOrTown = "mexico",
            state = "mexico",
            idFront = 51,
            idbusLine = "",
            idBrand = ""
        )

        else -> StopsSpainRequest(
            idFront = 100,
            country = "generic",
            state = "generic",
            cityOrTown = "generic",
            idLocalCompany = idLocalCompany.toString()
        )
    }

    fun getRequestByIdCompanyDetailLine(idLocalCompany: Int, idBusLine: String, state: String) =
        when (idLocalCompany) {
            AHORROBUS.idLocalCompany -> getAhorrobusDetailLineRequest(idBusLine, state)
            else -> getAwsDetailLineRequest(
                idBusLine,
                idLocalCompany
            )
        }

    fun getRequestByIdCompanyDetailStop(idLocalCompany: Int, idBusStop: String): DetailStopRequest {
        val request = getDetailStopBaseRequest(idLocalCompany.toString(), idBusStop)
        return when (idLocalCompany) {
            AHORROBUS.idLocalCompany -> request.copy(
                country = "intermedio",
                state = "intermedio",
                cityOrTown = "intermedio"
            )

            BENIDORM.idLocalCompany -> request.copy(
                country = "spain",
                state = "benidorm",
                cityOrTown = "benidorm"
            )

            RUBI.idLocalCompany -> request.copy(
                country = "spain",
                state = "rubi",
                cityOrTown = "rubi"
            )

            VIGO.idLocalCompany -> request.copy(
                country = "spain",
                state = "vigo",
                cityOrTown = "vigo"
            )

            else -> throw IllegalArgumentException("Unknown id company for regions request")
        }
    }

    fun getRequestByIdCompanyDetailRoute(idLocalCompany: Int, idBusLine: String, idPath: String) =
        when (idLocalCompany) {
            BENIDORM.idLocalCompany -> getBenidormDetailRouteRequest(idBusLine, idPath)
            else -> throw IllegalArgumentException("Unknown id company for regions request")
        }

    fun getRequestByTypeStops(idLocalCompany: Int, idBusline: String, tripCode: String) =
        TeoricByTypeStopRequest(
            idFront = 100,
            country = "españa",
            state = "provincia_segovia",
            cityOrTown = "segovia",
            idLocalCompany.toString(),
            idBusline,
            tripCode
        )


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

    private fun getDetailStopBaseRequest(idLocalCompany: String, idBusStop: String) =
        DetailStopRequest(
            idFront = 100,
            country = "",
            state = "",
            cityOrTown = "",
            idBusLine = "",
            idLocalCompany = idLocalCompany,
            idBusStop = idBusStop
        )

    fun getRequestByIdCompanyAws(idLocalCompany: Int, idBusLine: String, tripCode: String) =
        when (idLocalCompany) {
            AppId.SEGOVIA.idLocalCompany -> getSegoviaTeoricsByTypeStop(idBusLine, tripCode)
            else -> throw IllegalArgumentException("Unknown id company for regions request")
        }

    private fun getSegoviaTeoricsByTypeStop(idBusLine: String, tripCode: String) =
        TeoricByTypeStopRequest(
            idFront = 100,
            country = "españa",
            state = "provincia_segovia",
            cityOrTown = "segovia",
            idLocalCompany = "65",
            idBusLine = idBusLine,
            tripCode = tripCode
        )

    fun getRoutesByIdRequest(idLocalCompany: String, idBusLine: String, tripCode: String = "I") =
        RoutesByIdLineRequest(
            idFront = 60,
            country = "spain",
            state = "spain",
            cityOrTown = "spain",
            idLocalCompany = idLocalCompany,
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
            cityOrTown = "spain",
            country = "spain",
            idBusLine = idBusLine,
            idFront = 100,
            idLocalCompany = idLocalCompany.toString(),
            state = "spain",
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

    /**
     * Request to service ObtenerMapaParadas
     */
    fun getMapStopsRequestByIdCompany(idLocalCompany: Int): BaseRequest = BaseRequest(
        idFront = 100,
        country = "generic",
        state = "generic",
        cityOrTown = "generic",
        idLocalCompany = idLocalCompany.toString()
    )
}