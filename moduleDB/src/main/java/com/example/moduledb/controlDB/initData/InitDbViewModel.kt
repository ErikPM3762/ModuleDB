package com.example.moduledb.controlDB.initData

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moduledb.controlDB.data.local.daos.MDbLinesByMacroRegionDao
import com.example.moduledb.controlDB.data.local.daos.MDbLinesByRegionDao
import com.example.moduledb.controlDB.data.local.daos.MDbLinesDetailDao
import com.example.moduledb.controlDB.data.local.daos.MDbVersionInfoDao
import com.example.moduledb.controlDB.data.local.entities.MDbListLines
import com.example.moduledb.controlDB.data.local.entities.MDbListStops
import com.example.moduledb.controlDB.data.local.entities.MDbMacroRegions
import com.example.moduledb.controlDB.data.local.entities.MDdRegions
import com.example.moduledb.controlDB.domain.usecase.GetDetailLine
import com.example.moduledb.controlDB.domain.usecase.GetDetailLineById
import com.example.moduledb.controlDB.domain.usecase.GetLinesByMacroRegion
import com.example.moduledb.controlDB.domain.usecase.GetLinesByRegion
import com.example.moduledb.controlDB.domain.usecase.GetMacroRegions
import com.example.moduledb.controlDB.domain.usecase.GetPointsInterest
import com.example.moduledb.controlDB.domain.usecase.GetPointsRecharge
import com.example.moduledb.controlDB.domain.usecase.GetRegions
import com.example.moduledb.controlDB.domain.usecase.GetStopByBusLine
import com.example.moduledb.controlDB.domain.usecase.GetStopById
import com.example.moduledb.controlDB.domain.usecase.GetStops
import com.example.moduledb.controlDB.domain.usecase.routes.GetRouteDetailByIdLineAndIdPath
import com.example.moduledb.controlDB.domain.usecase.routes.GetRoutesByIdLine
import com.example.moduledb.controlDB.initData.viewState.MainViewState
import com.example.services.data.response.lines.ListLines
import com.example.services.data.response.macroRegions.MacroRegions
import com.example.services.utils.NetResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class InitDbViewModel @Inject constructor(
    private val getPointsInterest: GetPointsInterest,
    private val getPointsRecharge: GetPointsRecharge,
    private val getMacroRegions: GetMacroRegions,
    private val getRegions: GetRegions,
    private val getLinesByMacroRegion: GetLinesByMacroRegion,
    private val getStops: GetStops,
    private val getLinesByRegion: GetLinesByRegion,
    private val mDbVersionInfoDao: MDbVersionInfoDao,
    private val mDbLinesByMRList: MDbLinesByMacroRegionDao,
    private val mDbDetailLineById: MDbLinesDetailDao,
    private val mDbLinesByRList: MDbLinesByRegionDao,
    private val getStopsByBusLine: GetStopByBusLine,
    private val getStopsById: GetStopById,
    private val getRoutesByIdLine: GetRoutesByIdLine,
    private val getDetailLines: GetDetailLine,
    private val getDetailLinesById: GetDetailLineById,
    private val getRouteDetailByIdLineAndIdPath: GetRouteDetailByIdLineAndIdPath,
) : ViewModel() {

    //State para uso de JetpackCompose
    var stateUi by mutableStateOf(MainViewState())


    init {
        viewModelScope.launch(Dispatchers.IO) {
            mDbVersionInfoDao.insertOrUpdatePointInterestAndRecharge(0, 0)
        }
    }

    /**
     * Obtenemos los puntos de interes solo si la version es diferente a la guardada en local.
     * Retorna los puntos de interes
     * Es aplicable para los siguientes negocios: Ahorrobus
     */
    fun getPointsInterest() {
        stateUi = stateUi.copy(pointsOfInterest = emptyList())
        viewModelScope.launch(Dispatchers.IO) {
            getPointsInterest.invoke().collect { result ->
                when (result) {
                    is NetResult.Success -> {
                        stateUi = stateUi.copy(pointsOfInterest = result.data)
                    }

                    else -> {
                        Log.d("ViewModel", "Failed Call $result")
                    }
                }
            }
        }
    }


    /**
     * Obtenemos los puntos de recarga solo si la version es diferente a la guardada en local.
     * Retorna Los puntos de recarga
     * Es aplicable para los siguientes negocios: Ahorrobus
     */
    fun getPointsRecharge() {
        stateUi = stateUi.copy(pointsOfRecharge = emptyList())
        viewModelScope.launch(Dispatchers.IO) {
            getPointsRecharge.invoke().collect { result ->
                when (result) {
                    is NetResult.Success -> {
                        stateUi = stateUi.copy(pointsOfRecharge = result.data)
                    }
                    else -> {
                        Log.d("ViewModel", "Failed Call $result")
                    }
                }
            }
        }
    }


    /**
     * Funcionalidad abierta para ser utilizada por medio de la instancia del viewModel de manera externa
     * Obtener las macro regiones e iterar sobre la respuesta para guardar la lista de lineas por macroRegion
     */
    fun getMacroRegions(idLocalCompany: Int) {
        stateUi = stateUi.copy(macroRegions = emptyList())
        stateUi = stateUi.copy(linesByMacroRegion = emptyList())
        viewModelScope.launch(Dispatchers.IO) {
            getMacroRegions.invoke(idLocalCompany).collect() { result ->
                when (result) {
                    is NetResult.Success -> {
                        val macroRegions = result.data as List<MacroRegions>
                        stateUi = stateUi.copy(macroRegions = macroRegions)
                        for (macroRegion in macroRegions) {
                            viewModelScope.launch {
                                getLinesByMacroRegion.invoke(
                                    idLocalCompany, macroRegion.idMacroRegion
                                ).collect() { resultListLines ->
                                    when (resultListLines) {
                                        is NetResult.Success -> {
                                            val listLinesByMRegions =
                                                resultListLines.data as List<ListLines>
                                            stateUi = stateUi.copy(linesByMacroRegion = listLinesByMRegions)
                                            Log.e(
                                                "El tamaño de Las lineas es : ",
                                                "${listLinesByMRegions.size}"
                                            )
                                        }

                                        else -> {
                                            Log.e(
                                                "viewModel ",
                                                "error"
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    else -> {
                        Log.e(
                            "viewModel ",
                            "error 2"
                        )
                    }
                }
            }
        }
    }

    /**
     * Funcionalidad abierta para ser utilizada por medio de la instancia del viewModel de manera externa
     * Obtener las regiones e iterar sobre la respuesta para guardar la lista de lineas por Region
     */
    fun getRegions(idLocalCompany: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getRegions.invoke(idLocalCompany).collect() { result ->
                when (result) {
                    is NetResult.Success -> {
                        val regions = result.data as List<MDdRegions>
                        for (region in regions) {
                            viewModelScope.launch {
                                getLinesByRegion.invoke(idLocalCompany, region.idRegion)
                                    .collect() { resultListLines ->
                                        when (resultListLines) {
                                            is NetResult.Success -> {
                                                Log.e(
                                                    "Size lineas es : ",
                                                    "${resultListLines.data.size}"
                                                )
                                            }

                                            else -> {
                                                // Manejar el error si es necesario
                                            }
                                        }
                                    }
                            }
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    fun getListLines(idLocalCompany: Int) {
        viewModelScope.launch {
            getLinesByRegion.invoke(idLocalCompany, "").collect() { resultListLines ->
                when (resultListLines) {
                    is NetResult.Success -> {
                    }

                    else -> {
                        // Manejar el error si es necesario
                    }
                }
            }
        }
    }

    /**
     * Funcion publica para obtener el listado de detalle de linea para Ahorrobus
     */
    fun getDetailLinesByIdB(idLocalCompany: Int) {
        var detailLinesInvocationCount = 0
        viewModelScope.launch(Dispatchers.IO) {
            val linesByRegionList = mDbLinesByMRList.getAllMDbListLines()
            for (lines in linesByRegionList) {
                detailLinesInvocationCount++
                viewModelScope.launch {
                    getDetailLines.invoke(
                        idLocalCompany,
                        lines.idBusLine,
                        lines.macroRegions?.get(0)?.desMacroRegion as String
                    ).collect() { resulDetailLines ->
                        when (resulDetailLines) {
                            is NetResult.Success -> {
                                Log.e("Lineas Llamadas", detailLinesInvocationCount.toString())
                            }

                            else -> {
                                // Manejar el error si es necesario
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Funcion publica para obtener el listado de detalle de linea para Ahorrobus
     */
    fun getDetailLinesByIdX(idLocalCompany: Int) {
        var detailLinesInvocationCount = 0
        viewModelScope.launch(Dispatchers.IO) {
            val x = listOf("665", "674", "675")
            for (xx in x) {
                detailLinesInvocationCount++
                viewModelScope.launch {
                    getDetailLines.invoke(
                        idLocalCompany,
                        xx,
                        "Campeche"
                    )
                        .collect() { resulDetailLines ->
                            when (resulDetailLines) {
                                is NetResult.Success -> {
                                    Log.e("Lineas Llamadas", resulDetailLines.toString())
                                }

                                else -> {
                                    // Manejar el error si es necesario
                                }
                            }
                        }
                }
            }
        }
    }

    /**
     * Funcion publica para obtener el listado de lineas por MacroRegion
     */
    fun getLinesByMacroRegionDb(idMacroRegion: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = mDbLinesByMRList.getMDbListLinesById(idMacroRegion.toString())
            withContext(Dispatchers.Main) {

            }
        }
    }

    /**
     * Funcion publica para obtener el detalle de linea por idBusSae
     */
    fun getDetailLineByIdDb(idBusLine: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getDetailLinesById.invoke((139).toString())
            withContext(Dispatchers.Main) {
                Log.e("DetailLineById", result.toString())
            }
        }
    }

    /**
     * Funcion publica para obtener el listado de lineas por MacroRegion
     */
    fun getLinesByRegion(idMacroRegion: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = mDbLinesByMRList.getMDbListLinesById(idMacroRegion.toString())
            withContext(Dispatchers.Main) {

            }
        }
    }

    /**
     * Funcionalidad abierta para ser utilizada por medio de la instancia del viewModel de manera externa
     * Obtenemos la lista de las paradas de Oracle
     */
    fun getStops(idLocalCompany: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getStops.invoke(idLocalCompany).collect() { result ->
                when (result) {
                    is NetResult.Success -> {
                        val mdbListStops = result.data as List<MDbListStops>

                    }

                    else -> {}
                }
            }
        }
    }

    fun fetchStopsByBuslineCrossingId(buslineCrossingId: String) {
        viewModelScope.launch {
            val stopListResult = getStopsByBusLine.invoke(buslineCrossingId)
            val stopList: List<MDbListStops> = stopListResult as List<MDbListStops>

            for (stop in stopList) {
                Log.e("LIST_STOP_BY_ID", stop.toString())
            }
        }

        viewModelScope.launch {
            val stopListResult = getStopsById.invoke(1)
            val stop: MDbListStops? = stopListResult
            Log.e("STOP_BY_ID", stop.toString())
        }
    }


    fun demo(idLocalCompany: Int, idBusLineList: List<String>, state: String = "benidorm") =
        viewModelScope.launch {
            idBusLineList.forEach { idBusLine ->
                Log.e("Lineas Llamadas", "idBusLine $idBusLine")
                getDetailLines.invoke(
                    idLocalCompany, idBusLine, state
                ).collectIndexed { _, resulDetailLines ->
                    when (resulDetailLines) {
                        is NetResult.Success -> {
                            Log.e("Lineas Llamadas", resulDetailLines.toString())
                        }

                        else -> {
                            // Manejar el error si es necesario
                        }
                    }
                }

            }
        }

    fun demo2(idLocalCompany: Int, idBusLineList: List<String>) {
        for (idBusLine in idBusLineList) {
            viewModelScope.launch {
                getRoutesByIdLine.invoke(
                    idLocalCompany.toString(), idBusLine
                ).collect { resulDetailLines ->
                    when (resulDetailLines) {
                        is NetResult.Success -> {
                            resulDetailLines.data.forEach {
                                val idBusLine = it.idBusLine
                                val idPath = it.pathIdBusLine
                                getRouteDetailByIdLineAndIdPath.invoke(
                                    idLocalCompany,
                                    idBusLine, idPath
                                ).collect { resultDetailRoute ->
                                    when (resultDetailRoute) {
                                        is NetResult.Success -> {
                                            resultDetailRoute.data
                                        }

                                        else -> {
                                            // Manejar el error si es necesario
                                        }
                                    }
                                }

                            }
                            Log.e("Lineas Llamadas", resulDetailLines.toString())
                        }

                        else -> {
                            // Manejar el error si es necesario
                        }
                    }
                }

            }
        }
    }
}
