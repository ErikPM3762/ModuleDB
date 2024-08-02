package com.example.modulefirebase

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

internal class FirebaseAnalyticsHelper(private val analytics: FirebaseAnalytics) {

    fun logEvent(eventName: String, params: Map<String, Any>) {
        val bundle = Bundle()
        for ((key, value) in params) {
            when (value) {
                is String -> bundle.putString(key, value)
                is Int -> bundle.putInt(key, value)
                is Long -> bundle.putLong(key, value)
                is Double -> bundle.putDouble(key, value)
                is Boolean -> bundle.putBoolean(key, value)
            }
        }
        analytics.logEvent(eventName, bundle)
    }

    fun setUserProperty(propertyName: String, value: String) {
        analytics.setUserProperty(propertyName, value)
    }
}