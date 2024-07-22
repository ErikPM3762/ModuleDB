package com.example.moduledb

import android.app.Application
import android.content.Context
import com.example.modulefirebase.FirebaseManager
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App : Application() {

    init {
        System.loadLibrary("secure")
    }
    override fun onCreate() {
        super.onCreate()
        demo(this)
    }

    private fun demo(context: Context) {
        FirebaseManager.getInstance(context).evaluateMinAppVersionAndroid(
            currentAppVersion = "1.0.0",
            onNewUrl = { url -> println(url) },
            onShowUpdateDialog = { println(it) },
            onCancelled = { error -> println("Database error: $error") }
        )

        val firebaseManager = FirebaseManager.getInstance(context)
        firebaseManager.logEvent(
            "event_name",
            mapOf("param1" to "value1", "param2" to "value2")
        )
        firebaseManager.setUserProperty("property_name", "value")
    }

}