package com.example.taskyapplication.presentation.notes

import com.example.model.data.Note

data class NotesUiState (
    val notesList: List<Note> = emptyList(),
    val isLoading: Boolean = false
)

data class NoteUiState (
    val title: String = "",
    val content: String = "",
    val tags: List<String> = emptyList()
)
