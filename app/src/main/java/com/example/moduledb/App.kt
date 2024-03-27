package com.example.moduledb

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App : Application() {

    init {
        System.loadLibrary("secure")
    }
    override fun onCreate() {
        super.onCreate()
    }


}