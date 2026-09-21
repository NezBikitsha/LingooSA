package com.example.lingosa.ui.learn

//package com.example.lingosa.ui.learn

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lingosa.R
import com.example.lingosa.databinding.ActivityLearnBinding

class LearnActivity : AppCompatActivity() {

    private val TAG = "LearnActivity"
    private lateinit var binding: ActivityLearnBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "LearnActivity created")

        binding = ActivityLearnBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Learn"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}