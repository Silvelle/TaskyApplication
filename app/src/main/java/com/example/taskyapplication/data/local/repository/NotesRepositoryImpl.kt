package com.example.taskyapplication.data.local.repository

import com.example.taskyapplication.data.local.dao.NoteDao
import com.example.taskyapplication.data.local.entity.NoteEntity
import com.example.taskyapplication.data.mappers.NoteMapper
import com.example.taskyapplication.domain.model.Note
import com.example.taskyapplication.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesRepositoryImpl(private val noteDao: NoteDao) : NotesRepository {
    override suspend fun observeNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
            .map(NoteMapper::toDomainList)
    }

    override suspend fun observeNote(id: Long): Flow<Note?> {
        return noteDao.getNote(id).map { entity ->
            entity?.let(NoteMapper::toDomain)
        }
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insert(NoteMapper.toEntity(note))
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.delete(NoteMapper.toEntity(note))
    }

    override suspend fun updateNote(note: Note) {
        noteDao.update(NoteMapper.toEntity(note))
    }
}
