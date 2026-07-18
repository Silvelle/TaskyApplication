package com.example.taskyapplication.domain.model

data class Note(
    val id: String,
    val title: String,
    val content: String,
    val createdAt: Long,
    val updatedAt: Long,
    val isPinned: Boolean,
    val isArchieved: Boolean,
    val color: String?,
    val tags: List<String> = emptyList<String>()
)
