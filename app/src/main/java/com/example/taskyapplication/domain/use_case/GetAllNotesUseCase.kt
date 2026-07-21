package com.example.taskyapplication.domain.use_case

import com.example.taskyapplication.domain.model.Note
import com.example.taskyapplication.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class GetAllNotesUseCase(
    private val notesRepository: NotesRepository
) {
    suspend fun getAllNotes() : Flow<List<Note>> {
        return notesRepository.observeNotes()
    }
}