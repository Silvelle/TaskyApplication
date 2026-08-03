package com.example.taskyapplication.domain.use_case

import com.example.data.repository.NotesRepository
import com.example.model.data.Note
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase(
    private val notesRepository: NotesRepository
) {
    fun getAllNotes() : Flow<List<Note>> {
        return notesRepository.observeNotes()
    }
}
