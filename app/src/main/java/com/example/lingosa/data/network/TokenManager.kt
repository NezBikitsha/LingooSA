package com.example.lingosa.data.network

//package com.example.lingosa.data.network

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

/**
 * Manages JWT token storage in SharedPreferences
 * Singleton object for token operations
 */
object TokenManager {

    private const val TAG = "TokenManager"
    private const val PREF_NAME = "auth_prefs"
    private const val KEY_ACCESS_TOKEN = "access_token"
    private const val KEY_REFRESH_TOKEN = "refresh_token"

    private lateinit var preferences: SharedPreferences

    /**
     * Initialize TokenManager with application context
     * Must be called before using any other methods
     */
    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        Log.d(TAG, "TokenManager initialized")
    }

    /**
     * Save authentication tokens
     */
    fun saveTokens(access: String, refresh: String) {
        preferences.edit()
            .putString(KEY_ACCESS_TOKEN, access)
            .putString(KEY_REFRESH_TOKEN, refresh)
            .apply()
        Log.d(TAG, "Tokens saved successfully")
    }

    /**
     * Get the current access token
     */
    fun getAccessToken(): String? {
        return preferences.getString(KEY_ACCESS_TOKEN, null)
    }

    /**
     * Get the current refresh token
     */
    fun getRefreshToken(): String? {
        return preferences.getString(KEY_REFRESH_TOKEN, null)
    }

    /**
     * Clear all tokens (logout)
     */
    fun clearTokens() {
        preferences.edit()
            .remove(KEY_ACCESS_TOKEN)
            .remove(KEY_REFRESH_TOKEN)
            .apply()
        Log.d(TAG, "Tokens cleared")
    }

    /**
     * Check if user is currently logged in
     */
    fun isLoggedIn(): Boolean {
        return !getAccessToken().isNullOrEmpty()
    }
}