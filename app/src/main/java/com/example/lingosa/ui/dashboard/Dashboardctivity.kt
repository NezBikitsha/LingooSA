package com.example.lingosa.ui.dashboard

//package com.example.lingosa.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lingosa.databinding.ActivityDashboardBinding
import com.example.lingosa.ui.learn.LearnActivity
import com.example.lingosa.ui.settings.SettingsActivity

class DashboardActivity : AppCompatActivity() {

    private val TAG = "DashboardActivity"
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "DashboardActivity created")

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Lingo SA"

        binding.btnLearn.setOnClickListener {
            startActivity(Intent(this, LearnActivity::class.java))
        }

        binding.btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}