package com.example.modulefirebase

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.FirebaseDatabase

class FirebaseManager private constructor(context: Context) {

    private val TAG = this::class.java.simpleName
    private var analyticsHelper: FirebaseAnalyticsHelper? = null
    private var databaseHelper: FirebaseDatabaseHelper? = null

    init {
        try {
            analyticsHelper = FirebaseAnalyticsHelper(FirebaseAnalytics.getInstance(context))
            databaseHelper = FirebaseDatabaseHelper(FirebaseDatabase.getInstance())
        } catch (e: java.lang.IllegalStateException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun logEvent(eventName: String, params: Map<String, Any>) {
        analyticsHelper?.logEvent(eventName, params)
    }

    fun setUserProperty(propertyName: String, value: String) {
        analyticsHelper?.setUserProperty(propertyName, value)
    }

    fun evaluateMinAppVersionAndroid(
        currentAppVersion: String,
        onNewUrl: (String) -> Unit,
        onShowUpdateDialog: (Boolean) -> Unit,
        onCancelled: (error: String) -> Unit
    ) {
        databaseHelper?.evaluateMinAppVersionAndroid(
            currentAppVersion,
            onNewUrl,
            onShowUpdateDialog,
            onCancelled
        )
    }

    companion object {
        private var instance: FirebaseManager? = null

        fun getInstance(context: Context): FirebaseManager {
            if (instance == null) {
                instance = FirebaseManager(context)
            }
            return instance!!
        }
    }
}