package com.example.taskyapplication.domain.repository

import com.example.taskyapplication.domain.model.Note

import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun observeNotes(): Flow<List<Note>>
    suspend fun observeNote(id: Long): Flow<Note?>
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun updateNote(note: Note)
}