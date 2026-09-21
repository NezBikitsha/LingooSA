package com.example.lingosa.data.models

//package com.example.lingosa.data.models

/**
 * Learning module data model
 */
data class Module(
    val id: Int,
    val title: String,
    val description: String,
    val language: String,
    val difficulty: String,
    val estimatedTimeMinutes: Int,
    val lessonsCount: Int,
    val isDownloaded: Boolean = false
)

/**
 * Individual lesson within a module
 */
data class Lesson(
    val id: Int,
    val title: String,
    val isCompleted: Boolean = false,
    val content: List<LessonContent>
)

/**
 * Sealed class for different types of lesson content
 */
sealed class LessonContent {
    /**
     * A phrase with translation and optional audio
     */
    data class Phrase(
        val text: String,
        val translation: String,
        val audioUrl: String? = null
    ) : LessonContent()

    /**
     * A flashcard with optional mnemonic
     */
    data class Flashcard(
        val word: String,
        val translation: String,
        val mnemonic: String? = null
    ) : LessonContent()

    /**
     * Cultural content with video
     */
    data class CultureVideo(
        val title: String,
        val videoUrl: String,
        val description: String
    ) : LessonContent()
}