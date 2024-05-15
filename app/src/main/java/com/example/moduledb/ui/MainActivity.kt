package com.example.moduledb.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moduledb.controlDB.initData.InitDbViewModel
import com.example.moduledb.controlDB.initData.StopsViewModel
import com.example.moduledb.controlDB.utils.AppId
import com.example.services.utils.AppIdManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModuleDBTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(
    mainViewModel: InitDbViewModel = viewModel(),
    stopsViewModel: StopsViewModel = viewModel()
) {
    val state = mainViewModel.stateUi
    val context = LocalContext.current
    val idLocalCompany = AppIdManager.getIdLocalCompany()


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.CenterHorizontally),
            onClick = {
                mainViewModel.getPointsInterest()
            }) {
            Text("Obtener Puntos de interes")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.CenterHorizontally),
            onClick = {
                mainViewModel.getPointsRecharge()
            }) {
            Text("Obtener Puntos de recarga")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.CenterHorizontally),
            onClick = {
                mainViewModel.getMacroRegions(idLocalCompany)
            }) {
            Text("Obtener MacroRegiones")
        }


        // Observers
        LaunchedEffect(state.pointsOfInterest) {
            if (state.pointsOfInterest.isNotEmpty()) {
                Toast.makeText(context, "Puntos de Interés Actualizados!", Toast.LENGTH_SHORT).show()
            }
        }

        LaunchedEffect(state.pointsOfRecharge) {
            if (state.pointsOfRecharge.isNotEmpty()) {
                Toast.makeText(context, "Puntos de Recarga Actualizados!", Toast.LENGTH_SHORT).show()
            }
        }

        LaunchedEffect(state.macroRegions) {
            if (state.macroRegions.isNotEmpty()) {
                Toast.makeText(context, "Macro Regiones Actualizadas!", Toast.LENGTH_SHORT).show()
            }
        }

        LaunchedEffect(state.linesByMacroRegion) {
            if (state.linesByMacroRegion.isNotEmpty()) {
                Toast.makeText(context, "Lineas por macro Region Actualizadas!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun ModuleDBTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = lightColors(
            primary = Color.Blue,
            secondary = Color.Green
        )
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ModuleDBTheme {
        MainScreen()
    }
}
