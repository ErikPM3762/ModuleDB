package com.example.moduledb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.room.Room
import com.example.moduledb.controlDB.data.local.AppDataBase
import com.example.moduledb.controlDB.initData.InitDbViewModel
import com.example.moduledb.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: InitDbViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    lateinit var appDatabase: AppDataBase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.getPointsInterest()
        mainViewModel.getPointsRecharge()
        mainViewModel.getMacroRegions(11)


        binding.btnLISTMCRG.setOnClickListener {
            val idRegionText = binding.etxLine.text.toString()

            if (idRegionText.isNotEmpty()) {
                val idRegion = idRegionText.toLongOrNull()

                if (idRegion != null) {
                    mainViewModel.getLinesByMacroRegionDb(idRegion)
                } else {
                    Toast.makeText(this, "Ingrese un número válido", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Debes ingresar una región", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnPOR.setOnClickListener {
            mainViewModel.getPointsRecharge()
        }

        binding.btnMacroRegions.setOnClickListener {
            mainViewModel.getMacroRegions(5)
        }

        binding.btnListRegions.setOnClickListener {
            mainViewModel.getRegions(5)
        }

        binding.btnStops.setOnClickListener {
            mainViewModel.getStops(53)
        }


        mainViewModel.mdbListLines.observe(this) { mdbListLines ->
            for (mdbListLine in mdbListLines) {
                println("idBusLine: ${mdbListLine.idBusLine}")
            }
            binding.img3.visibility = View.VISIBLE
            binding.txtSizeLines.text = mdbListLines.size.toString()
        }

        mainViewModel.pointsOfInterestAvailable.observe(this) {
            binding.img1.visibility = View.VISIBLE
        }

        mainViewModel.pointsOfRechargeAvailable.observe(this) {
            binding.img2.visibility = View.VISIBLE
        }

        mainViewModel.mdbListMacroRegion.observe(this) {
            Toast.makeText(this, "Total de regiones ${it.size}", Toast.LENGTH_SHORT).show()
        }

        mainViewModel.mdbListAllLines.observe(this) {
            Toast.makeText(this, "Total de lineas ${it.size}", Toast.LENGTH_SHORT).show()
        }

        mainViewModel.mdbListStops.observe(this){
            Toast.makeText(this, "Total de paradas ${it.size}", Toast.LENGTH_SHORT).show()
        }


        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "module_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }


}