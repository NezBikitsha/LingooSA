package com.example.lingosa.data.repository

// package com.example.lingosa.data.repository

import android.util.Log
import com.example.lingosa.data.models.User
import com.example.lingosa.data.network.ApiService
import com.example.lingosa.data.network.LoginRequest
import com.example.lingosa.data.network.RegisterRequest
import com.example.lingosa.data.network.TokenManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for authentication-related operations
 * Handles registration, login, and token management
 */
@Singleton
class AuthRepository @Inject constructor(
    private val apiService: ApiService
) {

    private val TAG = "AuthRepository"

    /**
     * Register a new user
     * @param email User's email address
     * @param username User's chosen username
     * @param password User's password (will be hashed on backend)
     * @param fullName User's full name
     * @return Flow with registration result
     */
    fun register(
        email: String,
        username: String,
        password: String,
        fullName: String
    ): Flow<Result<User>> = flow {
        try {
            Log.d(TAG, "Registering user: $email")
            val response = apiService.register(
                RegisterRequest(email, username, password, fullName)
            )

            if (response.isSuccessful) {
                response.body()?.let { userResponse ->
                    Log.d(TAG, "Registration successful for: ${userResponse.email}")
                    val user = User(
                        id = userResponse.id,
                        email = userResponse.email,
                        username = userResponse.username,
                        fullName = userResponse.fullName
                    )
                    emit(Result.success(user))
                } ?: emit(Result.failure(Exception("Empty response")))
            } else {
                val error = response.errorBody()?.string() ?: "Unknown error"
                Log.e(TAG, "Registration failed: $error")
                emit(Result.failure(Exception("Registration failed: $error")))
            }
        } catch (e: IOException) {
            Log.e(TAG, "Network error during registration: ${e.message}")
            emit(Result.failure(Exception("Network error. Please check your connection.")))
        } catch (e: HttpException) {
            Log.e(TAG, "HTTP error during registration: ${e.message}")
            emit(Result.failure(Exception("Server error. Please try again later.")))
        } catch (e: Exception) {
            Log.e(TAG, "Unexpected error during registration: ${e.message}")
            emit(Result.failure(Exception("An unexpected error occurred.")))
        }
    }

    /**
     * Login existing user
     * @param email User's email address
     * @param password User's password
     * @return Flow with login result containing user and tokens
     */
    fun login(email: String, password: String): Flow<Result<Pair<User, String>>> = flow {
        try {
            Log.d(TAG, "Logging in user: $email")
            val response = apiService.login(LoginRequest(email, password))

            if (response.isSuccessful) {
                response.body()?.let { authResponse ->
                    Log.d(TAG, "Login successful for: ${authResponse.user.email}")
                    // Store tokens
                    TokenManager.saveTokens(authResponse.access, authResponse.refresh)
                    emit(Result.success(Pair(authResponse.user, authResponse.access)))
                } ?: emit(Result.failure(Exception("Empty response")))
            } else {
                val error = response.errorBody()?.string() ?: "Invalid credentials"
                Log.e(TAG, "Login failed: $error")
                emit(Result.failure(Exception("Login failed: Invalid email or password")))
            }
        } catch (e: IOException) {
            Log.e(TAG, "Network error during login: ${e.message}")
            emit(Result.failure(Exception("Network error. Please check your connection.")))
        } catch (e: HttpException) {
            Log.e(TAG, "HTTP error during login: ${e.message}")
            emit(Result.failure(Exception("Server error. Please try again later.")))
        } catch (e: Exception) {
            Log.e(TAG, "Unexpected error during login: ${e.message}")
            emit(Result.failure(Exception("An unexpected error occurred.")))
        }
    }

    /**
     * Logout current user
     */
    fun logout() {
        Log.d(TAG, "Logging out user")
        TokenManager.clearTokens()
    }

    /**
     * Check if user is currently logged in
     */
    fun isLoggedIn(): Boolean {
        return TokenManager.isLoggedIn()
    }
}