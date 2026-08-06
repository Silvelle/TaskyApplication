package com.example.model.data

data class Note(
    val id: Long,
    val title: String,
    val document: NoteDocument,
    val createdAt: Long,
    val updatedAt: Long,
    val isPinned: Boolean,
    val isArchieved: Boolean,
    val color: String? = "",
    val tags: List<String> = emptyList<String>()
) {
    val plainText: String
        get() = document.text
}