package com.example.lingosa.data.network

//package com.example.lingosa.data.network

import com.example.lingosa.data.network.ApiService.*
import retrofit2.Response
import retrofit2.http.*



/**
 * Retrofit API service interface
 * Defines all API endpoints for the app
 */
interface ApiService {

    // ===== AUTH ENDPOINTS =====

    /**
     * Register a new user
     */
    @POST("auth/register/")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<UserResponse>

    /**
     * Login existing user
     */
    @POST("auth/login/")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<AuthResponse>

    // ===== USER PROFILE =====

    /**
     * Get current user's profile
     */
    @GET("users/profile/")
    suspend fun getProfile(): Response<UserProfile>

    /**
     * Update user profile
     */
    @PUT("users/profile/")
    suspend fun updateProfile(
        @Body request: ProfileUpdateRequest
    ): Response<UserProfile>

    // ===== LEARNING MODULES =====

    /**
     * Get all learning modules
     */
    @GET("modules/")
    suspend fun getModules(
        @Query("language") language: String? = null
    ): Response<List<Module>>

    /**
     * Get detailed content for a specific module
     */
    @GET("modules/{id}/")
    suspend fun getModuleDetail(
        @Path("id") moduleId: Int
    ): Response<ModuleDetail>

    /**
     * Update user progress on a module
     */
    @POST("modules/{id}/progress/")
    suspend fun updateProgress(
        @Path("id") moduleId: Int,
        @Body request: ProgressRequest
    ): Response<Unit>
}

// ===== REQUEST/RESPONSE DTOs =====

/**
 * Registration request body
 */
data class RegisterRequest(
    val email: String,
    val username: String,
    val password: String,
    val fullName: String
)

/**
 * Login request body
 */
data class LoginRequest(
    val email: String,
    val password: String
)

/**
 * Authentication response with JWT tokens
 */
data class AuthResponse(
    val refresh: String,
    val access: String,
    val user: User
)

/**
 * User response after registration
 */
data class UserResponse(
    val id: Int,
    val email: String,
    val username: String,
    val fullName: String,
    val dateJoined: String
)

/**
 * Profile update request body
 */
data class ProfileUpdateRequest(
    val fullName: String? = null,
    val bio: String? = null,
    val preferredLanguage: String? = null,
    val learningLanguage: String? = null,
    val notificationsEnabled: Boolean? = null
)

/**
 * User profile response
 */
data class UserProfile(
    val id: Int,
    val email: String,
    val username: String,
    val fullName: String,
    val bio: String?,
    val profilePictureUrl: String?,
    val preferredLanguage: String,
    val learningLanguage: String,
    val level: Int,
    val streakDays: Int,
    val notificationsEnabled: Boolean
)

/**
 * Module detail response
 */
data class ModuleDetail(
    val id: Int,
    val title: String,
    val description: String,
    val language: String,
    val difficulty: String,
    val lessons: List<Lesson>,
    val flashcards: List<LessonContent.Flashcard>,
    val cultureContent: LessonContent.CultureVideo?
)

/**
 * Progress update request
 */
data class ProgressRequest(
    val lessonId: Int,
    val isCompleted: Boolean
)