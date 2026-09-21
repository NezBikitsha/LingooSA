package com.example.lingosa.ui.settings

//package com.example.lingosa.ui.settings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lingosa.data.models.User
import com.example.lingosa.data.network.ApiClient
import com.example.lingosa.data.network.ProfileUpdateRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for Settings
 */
@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {

    private val TAG = "SettingsViewModel"

    private val _profile = MutableLiveData<User?>(null)
    val profile: LiveData<User?> = _profile

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    /**
     * Load user profile
     */
    fun loadProfile() {
        Log.d(TAG, "Loading profile")
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = ApiClient.apiService.getProfile()
                if (response.isSuccessful) {
                    response.body()?.let { profile ->
                        val user = User(
                            id = profile.id,
                            email = profile.email,
                            username = profile.username,
                            fullName = profile.fullName,
                            bio = profile.bio ?: "",
                            preferredLanguage = profile.preferredLanguage,
                            learningLanguage = profile.learningLanguage,
                            level = profile.level,
                            streakDays = profile.streakDays,
                            notificationsEnabled = profile.notificationsEnabled
                        )
                        _profile.value = user
                        Log.d(TAG, "Profile loaded for: ${user.email}")
                    }
                } else {
                    _errorMessage.value = "Failed to load profile"
                    Log.e(TAG, "API error loading profile")
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error loading profile: ${e.message}"
                Log.e(TAG, "Error loading profile: ${e.message}")
            }
            _isLoading.value = false
        }
    }

    /**
     * Update user profile
     */
    fun updateProfile(fullName: String, bio: String) {
        Log.d(TAG, "Updating profile")
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val request = ProfileUpdateRequest(
                    fullName = fullName,
                    bio = bio
                )
                val response = ApiClient.apiService.updateProfile(request)
                if (response.isSuccessful) {
                    Log.d(TAG, "Profile updated successfully")
                    loadProfile() // Reload updated profile
                } else {
                    _errorMessage.value = "Failed to update profile"
                    Log.e(TAG, "API error updating profile")
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error updating profile: ${e.message}"
                Log.e(TAG, "Error updating profile: ${e.message}")
            }
            _isLoading.value = false
        }
    }
}