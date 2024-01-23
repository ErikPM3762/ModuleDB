package com.example.moduledb.controlDB.utils

import com.example.moduledb.controlDB.data.remote.request.LinesListRequest
import com.example.moduledb.controlDB.data.remote.request.MacroRegionsRequest
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
        AppId.BENIDORM.idLocalCompany -> getBenidormRequest()
        AppId.AHORROBUS.idLocalCompany -> getAhorrobusRequest()
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
        idFront = 51
    )

    fun getRequestByIdCompanyListLines(idLocalCompany: Int, idMacroRegion: String) = when (idLocalCompany) {
        AppId.BENIDORM.idLocalCompany -> getBenidormListLinesRequest(idMacroRegion)
        AppId.AHORROBUS.idLocalCompany -> getAhorrobusListLinesRequest(idMacroRegion)
        else -> throw IllegalArgumentException("Unknown id company for regions request")
    }

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
}