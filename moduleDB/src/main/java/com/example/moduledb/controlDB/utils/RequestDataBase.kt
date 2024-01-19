package com.example.moduledb.controlDB.utils

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
}