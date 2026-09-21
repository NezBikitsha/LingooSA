package com.example.lingosa.ui.auth

//package com.example.lingosa.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.lingosa.databinding.ActivityAuthBinding
import com.example.lingosa.ui.dashboard.DashboardActivity
import com.example.lingosa.utils.NetworkUtils
import com.google.android.material.snackbar.Snackbar

class AuthActivity : AppCompatActivity() {

    private val TAG = "AuthActivity"
    private lateinit var binding: ActivityAuthBinding
    private val authViewModel: AuthViewModel by viewModels()

    private var isLoginMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "AuthActivity created")

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        Log.d(TAG, "Setting up UI")

        binding.btnToggleMode.setOnClickListener {
            isLoginMode = !isLoginMode
            Log.d(TAG, "Toggling mode: isLoginMode = $isLoginMode")
            updateUIMode()
        }

        binding.btnSubmit.setOnClickListener {
            Log.d(TAG, "Submit button clicked")
            handleSubmit()
        }

        updateUIMode()
    }

    private fun updateUIMode() {
        if (isLoginMode) {
            binding.tvTitle.text = "Welcome Back"
            binding.btnSubmit.text = "Sign In"
            binding.btnToggleMode.text = "Don't have an account? Sign Up"
            binding.layoutRegister.visibility = View.GONE
            binding.tilConfirmPassword.visibility = View.GONE
        } else {
            binding.tvTitle.text = "Create Account"
            binding.btnSubmit.text = "Sign Up"
            binding.btnToggleMode.text = "Already have an account? Sign In"
            binding.layoutRegister.visibility = View.VISIBLE
            binding.tilConfirmPassword.visibility = View.VISIBLE
        }
    }

    private fun handleSubmit() {
        authViewModel.clearError()

        if (!NetworkUtils.isNetworkAvailable(this)) {
            showError("No internet connection. Please check your network.")
            return
        }

        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (isLoginMode) {
            authViewModel.login(email, password)
        } else {
            val username = binding.etUsername.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()
            val fullName = binding.etFullName.text.toString().trim()
            authViewModel.register(email, username, password, confirmPassword, fullName)
        }
    }

    private fun observeViewModel() {
        authViewModel.authState.observe(this) { state ->
            when (state) {
                is AuthState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnSubmit.isEnabled = false
                }
                is AuthState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnSubmit.isEnabled = true
                    navigateToDashboard()
                }
                is AuthState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnSubmit.isEnabled = true
                }
                else -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnSubmit.isEnabled = true
                }
            }
        }

        authViewModel.errorMessage.observe(this) { error ->
            if (!error.isNullOrEmpty()) {
                showError(error)
            }
        }
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    private fun navigateToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}