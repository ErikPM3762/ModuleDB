package com.example.moduledb.controlDB.initData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moduledb.controlDB.data.DataResult
import com.example.moduledb.controlDB.domain.usecase.GetDetailStopById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StopsViewModel @Inject constructor(
    private val getDetailStopById : GetDetailStopById
) : ViewModel()  {



    fun getDetailOfStopById() {
        viewModelScope.launch {
            /*
            getDetailStopById(11, "718").collect { result ->
                when (result) {
                    is DataResult.Success -> println("${result.data}")
                    is DataResult.Error -> println("Error al traer Datos")
                    else -> {println("Error al traer Datos")}
                }
            }

             */
        }

    }
}