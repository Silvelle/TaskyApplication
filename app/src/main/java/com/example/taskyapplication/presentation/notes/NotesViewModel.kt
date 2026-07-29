package com.example.taskyapplication.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskyapplication.domain.model.Note
import com.example.taskyapplication.domain.repository.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewModel (
    private val notesRepository: NotesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(NotesUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadNotes()
    }


    fun loadNotes() {
        viewModelScope.launch {
            notesRepository
                .observeNotes()
                .onEach { notes ->
                    _uiState.update {
                        it.copy(
                            notesList = notes,
                            isLoading = false,
                        )
                    }
                }
        }
    }
    fun addNote(note: Note) {
        viewModelScope.launch {
            notesRepository.insertNote(note)
        }
    }
    fun deleteNote(note: Note) {
        viewModelScope.launch {
            notesRepository.deleteNote(note)
        }
    }
}


