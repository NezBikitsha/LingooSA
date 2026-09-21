package com.example.lingosa.ui.auth

//package com.example.lingosa.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lingosa.data.models.User
import com.example.lingosa.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val TAG = "AuthViewModel"
    private val authRepository = AuthRepository()

    private val _authState = MutableLiveData<AuthState>(AuthState.Idle)
    val authState: LiveData<AuthState> = _authState

    private val _user = MutableLiveData<User?>(null)
    val user: LiveData<User?> = _user

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    fun register(email: String, username: String, password: String, confirmPassword: String, fullName: String) {
        when {
            email.isBlank() -> { _errorMessage.value = "Email cannot be empty"; return }
            username.isBlank() -> { _errorMessage.value = "Username cannot be empty"; return }
            fullName.isBlank() -> { _errorMessage.value = "Full name cannot be empty"; return }
            password.isBlank() -> { _errorMessage.value = "Password cannot be empty"; return }
            password != confirmPassword -> { _errorMessage.value = "Passwords do not match"; return }
            password.length < 6 -> { _errorMessage.value = "Password must be at least 6 characters"; return }
        }

        viewModelScope.launch {
            _authState.value = AuthState.Loading
            authRepository.register(email, username, password, fullName).collect { result ->
                result.onSuccess { user ->
                    _user.value = user
                    _authState.value = AuthState.Success
                    _errorMessage.value = null
                }.onFailure { error ->
                    _authState.value = AuthState.Error
                    _errorMessage.value = error.message ?: "Registration failed"
                }
            }
        }
    }

    fun login(email: String, password: String) {
        when {
            email.isBlank() -> { _errorMessage.value = "Email cannot be empty"; return }
            password.isBlank() -> { _errorMessage.value = "Password cannot be empty"; return }
        }

        viewModelScope.launch {
            _authState.value = AuthState.Loading
            authRepository.login(email, password).collect { result ->
                result.onSuccess { (user, _) ->
                    _user.value = user
                    _authState.value = AuthState.Success
                    _errorMessage.value = null
                }.onFailure { error ->
                    _authState.value = AuthState.Error
                    _errorMessage.value = error.message ?: "Login failed"
                }
            }
        }
    }

    fun logout() {
        authRepository.logout()
        _user.value = null
        _authState.value = AuthState.Idle
        _errorMessage.value = null
    }

    fun clearError() {
        _errorMessage.value = null
    }
}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    object Error : AuthState()
}