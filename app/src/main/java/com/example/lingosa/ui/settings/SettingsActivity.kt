package com.example.lingosa.ui.settings

//package com.example.lingosa.ui.settings

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lingosa.R
import com.example.lingosa.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private val TAG = "SettingsActivity"
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "SettingsActivity created")

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Settings"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}