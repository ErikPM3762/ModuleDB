package com.example.moduledb.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.moduledb.controlDB.data.local.AppDataBase
import com.example.moduledb.controlDB.initData.InitDbViewModel
import com.example.moduledb.controlDB.initData.StopsViewModel
import com.example.moduledb.controlDB.utils.AppId
import com.example.moduledb.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: InitDbViewModel by viewModels()
    private val stopsViewModel: StopsViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    lateinit var appDatabase: AppDataBase
    private val idLocalCompany = AppId.RUBI.idLocalCompany


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        stopsViewModel.fetchTheoricByTypeStop()
        clickListeners()
        initData()
        observers()
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



        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "module_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    private fun clickListeners() {
        binding.apply {
            btnPOR.setOnClickListener { mainViewModel.getPointsRecharge() }
            btnMacroRegions.setOnClickListener { mainViewModel.getMacroRegions(idLocalCompany) }
            btnListRegions.setOnClickListener { mainViewModel.getRegions(idLocalCompany) }
            btnStops.setOnClickListener { mainViewModel.getStops(idLocalCompany) }
            btnLinesDetailByRegion.setOnClickListener {
                mainViewModel.getDetailLinesByIdB(
                    idLocalCompany
                )
            }
            btnLines.setOnClickListener { mainViewModel.getListLines(idLocalCompany) }
        }
    }

    private fun initData() {
        mainViewModel.demo_getAllLines(5)
    }

    private fun observers() {

        mainViewModel.mdbListLines.observe(this) { mdbListLines ->
            for (mdbListLine in mdbListLines) {
                //println("idBusLine: ${mdbListLine.idBusLine}")
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

        mainViewModel.mdbListStops.observe(this) {
            Toast.makeText(this, "Total de paradas ${it.size}", Toast.LENGTH_SHORT).show()
        }

    }


}