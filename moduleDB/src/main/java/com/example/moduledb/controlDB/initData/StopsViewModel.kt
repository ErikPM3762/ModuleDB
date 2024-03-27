package com.example.moduledb.controlDB.initData

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moduledb.controlDB.data.DataResult
import com.example.moduledb.controlDB.domain.usecase.GetDetailStopById
import com.example.moduledb.controlDB.domain.usecase.GetTheoricByIdStop
import com.example.moduledb.controlDB.domain.usecase.GetTheoricByTypeStop
import com.example.moduledb.controlDB.utils.NetResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StopsViewModel @Inject constructor(
    private val getDetailStopById : GetDetailStopById,
    private val getTheoricByTypeStop : GetTheoricByTypeStop,
    private val getTheoricByIdStop : GetTheoricByIdStop
) : ViewModel()  {



    fun getDetailOfStopById() {
        viewModelScope.launch {
            getDetailStopById(11, "718").collect { result ->
                when (result) {
                    is DataResult.Success -> println("${result.data}")
                    is DataResult.Error -> println("Error al traer Datos")
                    else -> {println("Error al traer Datos")}
                }
            }
        }
    }

    fun fetchTheoricByTypeStop() {
        viewModelScope.launch {
            getTheoricByTypeStop(51, "L-1","I").collect(){ result ->
                when (result) {
                    is NetResult.Success -> {
                        println("${result.data}")
                        val theoricsStops = getTheoricByIdStop("218", "L-1", "V")
                        println("No existe parada ${theoricsStops}")
                    }
                    is NetResult.Error -> println("Error al traer Datos")
                    else -> {}
                }
            }
        }
    }
}