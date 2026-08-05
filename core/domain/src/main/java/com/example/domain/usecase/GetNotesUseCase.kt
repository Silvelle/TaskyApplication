package com.example.domain.usecase

import com.example.data.repository.NotesRepository
import com.example.model.data.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetNotesUseCase @Inject constructor(
    private val notesRepository: NotesRepository,
) {
    operator fun invoke(): Flow<List<Note>> = notesRepository.observeNotes()
}
