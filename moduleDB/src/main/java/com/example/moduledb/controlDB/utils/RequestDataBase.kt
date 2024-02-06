@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.example.moduledb.controlDB.utils

import android.util.Log
import com.example.moduledb.controlDB.data.remote.request.LinesListRequest
import com.example.moduledb.controlDB.data.remote.request.MacroRegionsRequest
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

    fun getRequestByIdCompanyListLines(idLocalCompany: Int, idMacroRegion: String) = when (idLocalCompany) {
        AppId.BENIDORM.idLocalCompany -> getBenidormListLinesRequest(idMacroRegion)
        AppId.AHORROBUS.idLocalCompany -> getAhorrobusListLinesRequest(idMacroRegion)
        else -> throw IllegalArgumentException("Unknown id company for regions request")
    }

    fun getRequestByIdCompanyStops(idLocalCompany: Int) = when (idLocalCompany) {
        //AppId.BENIDORM.idLocalCompany -> getAhorrobusStopsRequest()
        AppId.AHORROBUS.idLocalCompany -> getAhorrobusStopsRequest()
        AppId.OURENSE.idLocalCompany -> getOurenseStopsRequest()
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

    fun getRequestByIdCompanyAws(idLocalCompany: Int, idBusLine: String, tripCode: String) = when (idLocalCompany) {
        AppId.SEGOVIA.idLocalCompany -> getSegoviaTeoricsByTypeStop(idBusLine,tripCode)
        else -> throw IllegalArgumentException("Unknown id company for regions request")
    }
    private fun getSegoviaTeoricsByTypeStop(idBusLine: String, tripCode: String) = TeoricByTypeStopSegoviaRequest(
        idFront = 100,
        country = "españa",
        state = "provincia_segovia",
        cityOrTown = "segovia",
        idLocalCompany = "65",
        idBusLine = idBusLine,
        tripCode = tripCode
    )
}