package com.example.lingosa

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

//package com.example.lingosa

import android.content.Intent
//import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lingosa.data.network.TokenManager
import com.example.lingosa.ui.dashboard.DashboardActivity

//import com.example.lingosa.ui.auth.AuthActivity
//import com.example.lingosa.ui.dashboard.DashboardActivity
//import com.example.lingosa.data.network.TokenManager

/**
 * Main Activity
 * Acts as a launcher and directs users to the appropriate screen
 */
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "MainActivity created")

        // Initialize TokenManager
        TokenManager.init(applicationContext)

        // Check if user is logged in
        if (TokenManager.isLoggedIn()) {
            Log.d(TAG, "User is logged in, navigating to Dashboard")
            startActivity(Intent(this, DashboardActivity::class.isAbstract))
        } else {
            Log.d(TAG, "User is not logged in, navigating to Login")
            startActivity(Intent(this, AuthActivity::class.kclass))
        }

        finish()
    }
}