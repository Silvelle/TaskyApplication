package com.example.feature.note.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.NotesRepository
import com.example.domain.usecase.GetNotesUseCase
import com.example.model.data.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class NotesViewModel @Inject constructor(
    private val notesRepository: NotesRepository,
    private val getNotes: GetNotesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(NotesUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    init {
        loadNotes()
    }

    private fun loadNotes() {
        viewModelScope.launch {
            getNotes().collect { notes ->
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
