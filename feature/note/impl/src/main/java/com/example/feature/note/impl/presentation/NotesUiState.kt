package com.example.feature.note.impl.presentation

import com.example.model.data.Note
import com.example.model.data.NoteDocument

internal data class NotesUiState(
    val notesList: List<Note> = emptyList(),
    val isLoading: Boolean = false,
)

internal data class NoteUiState(
    val title: String = "",
    val document: NoteDocument = NoteDocument(),
    val tags: List<String> = emptyList(),
)
