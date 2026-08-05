package com.example.feature.note.impl.presentation

import com.example.model.data.Note

internal data class NotesUiState(
    val notesList: List<Note> = emptyList(),
    val isLoading: Boolean = false,
)

internal data class NoteUiState(
    val title: String = "",
    val content: String = "",
    val tags: List<String> = emptyList(),
)
