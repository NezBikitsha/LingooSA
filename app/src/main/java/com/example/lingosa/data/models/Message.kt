package com.example.lingosa.data.models

//package com.example.lingosa.data.models

/**
 * Message data model for chat functionality
 */
data class Message(
    val id: Int = 0,
    val senderId: Int = 0,
    val senderName: String = "",
    val receiverId: Int = 0,
    val receiverName: String = "",
    val content: String = "",
    val timestamp: String = "",
    val isSentByMe: Boolean = false,
    val isRead: Boolean = false,
    val isPending: Boolean = false  // For offline mode
)

/**
 * Conversation preview for chat list
 */
data class Conversation(
    val contactId: Int,
    val contactName: String,
    val lastMessage: String,
    val timestamp: String,
    val unreadCount: Int = 0,
    val profilePictureUrl: String? = null
)