package com.example.taskyapplication.data.local.repository

import com.example.taskyapplication.data.local.entity.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun getAllNotesStream(): Flow<Flow<Note>>
    suspend fun getNoteStream(id: String): Flow<Note?>
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun updateNote(note: Note)
}