package com.example.moduledb

import android.app.Application
import com.example.services.utils.AppId
import com.example.services.utils.AppIdManager
import com.example.services.utils.Environment
import com.example.services.utils.EnvironmentManager
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App : Application() {

    init {
        System.loadLibrary("secure")
    }
    override fun onCreate() {
        super.onCreate()
      setupConfigurationApp()
    }

    private fun setupConfigurationApp(){
        EnvironmentManager.setEnvironment(Environment.PROD)
        AppIdManager.setIdLocalCompany(AppId.AHORROBUS.idLocalCompany)
    }
}
