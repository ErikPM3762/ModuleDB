package com.example.moduledb.controlDB.controlInitDb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moduledb.controlDB.usecase.InitDbAhorrobus
import kotlinx.coroutines.launch

class ControlDbViewModel(
    val initDBAhorrobus : InitDbAhorrobus
) : ViewModel(){




    /**
     * Aqui vamos a inicializar la base de datos sin esperar datos de retorno
     */
    fun createDBAhorrobus(){
        viewModelScope.launch {
            initDBAhorrobus.initializeDatabase()
        }
    }
}