package com.example.moduledb.data.server.remote


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import javax.inject.Inject

class LiveNetworkMonitor @Inject constructor(private val applicationContext: Context)  :
    NetworkMonitor {

    override val isConnected: Boolean
        get() {
            val cm = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            return activeNetwork?.isConnectedOrConnecting == true
        }
}