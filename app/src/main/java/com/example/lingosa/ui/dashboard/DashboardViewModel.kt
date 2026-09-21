package com.example.lingosa.ui.dashboard

//package com.example.lingosa.ui.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lingosa.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for Dashboard
 */
@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val TAG = "DashboardViewModel"

    /**
     * Logout current user
     */
    fun logout() {
        Log.d(TAG, "Logging out")
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}