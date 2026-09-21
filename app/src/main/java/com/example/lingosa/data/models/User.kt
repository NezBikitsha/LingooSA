package com.example.lingosa.data.models


import android.os.Parcelable
//import kotlinx.parcelize.Parcelize

/**
 * User data model representing an app user
 * Implements Parcelable for passing between activities
 */
@Parcelize
data class User(
    val id: Int = 0,
    val email: String = "",
    val username: String = "",
    val fullName: String = "",
    val bio: String = "",
    val profilePictureUrl: String = "",
    val preferredLanguage: String = "en",
    val learningLanguage: String = "zu",
    val level: Int = 1,
    val streakDays: Int = 0,
    val notificationsEnabled: Boolean = true
) : Parcelable