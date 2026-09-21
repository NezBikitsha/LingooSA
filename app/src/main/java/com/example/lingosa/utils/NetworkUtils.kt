package com.example.lingosa.utils

//package com.example.lingosa.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log

/**
 * Network utility functions
 */
object NetworkUtils {

    private const val TAG = "NetworkUtils"

    /**
     * Check if network is available
     * @param context Application context
     * @return true if network is available
     */
    fun isNetworkAvailable(context: Context): Boolean {
        Log.d(TAG, "Checking network availability")

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.d(TAG, "Network available: WiFi")
                    true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.d(TAG, "Network available: Cellular")
                    true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.d(TAG, "Network available: Ethernet")
                    true
                }
                else -> {
                    Log.d(TAG, "No network available")
                    false
                }
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo
            val isAvailable = networkInfo != null && networkInfo.isConnected
            Log.d(TAG, "Network available (pre-M): $isAvailable")
            return isAvailable
        }
    }
}