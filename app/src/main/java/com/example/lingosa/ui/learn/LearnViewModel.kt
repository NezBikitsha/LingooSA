package com.example.lingosa.ui.learn

//package com.example.lingosa.ui.learn

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lingosa.data.models.Module
import com.example.lingosa.data.network.ApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for Learning modules
 */
@HiltViewModel
class LearnViewModel @Inject constructor() : ViewModel() {

    private val TAG = "LearnViewModel"

    private val _modules = MutableLiveData<List<Module>>(emptyList())
    val modules: LiveData<List<Module>> = _modules

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    /**
     * Load learning modules from API
     */
    fun loadModules() {
        Log.d(TAG, "Loading modules")
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = ApiClient.apiService.getModules()
                if (response.isSuccessful) {
                    response.body()?.let {
                        _modules.value = it
                        Log.d(TAG, "Loaded ${it.size} modules")
                    }
                } else {
                    _errorMessage.value = "Failed to load modules"
                    Log.e(TAG, "API error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error loading modules: ${e.message}"
                Log.e(TAG, "Error loading modules: ${e.message}")
            }
            _isLoading.value = false
        }
    }
}