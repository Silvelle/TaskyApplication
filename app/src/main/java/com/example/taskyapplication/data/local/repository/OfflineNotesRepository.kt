package com.example.taskyapplication.data.local.repository

import com.example.taskyapplication.data.local.dao.NoteDao
import com.example.taskyapplication.data.local.entity.Note
import kotlinx.coroutines.flow.Flow

class OfflineNotesRepository(private val noteDao: NoteDao) : NotesRepository {
    override suspend fun getAllNotesStream(): Flow<Flow<Note>> = noteDao.getAllItems()
    override suspend fun getNoteStream(id: String): Flow<Note?> = noteDao.getItem(id)
    override suspend fun insertNote(note: Note) = noteDao.insert(note)
    override suspend fun deleteNote(note: Note) = noteDao.delete(note)
    override suspend fun updateNote(note: Note) = noteDao.update(note)
}