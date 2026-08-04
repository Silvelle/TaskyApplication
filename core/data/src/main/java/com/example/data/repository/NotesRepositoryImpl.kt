package com.example.data.repository

import com.example.data.mapper.NoteMapper
import com.example.database.dao.NoteDao
import com.example.model.data.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.reflect.Constructor
import javax.inject.Inject

internal class NotesRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
) : NotesRepository {
    override fun observeNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
            .map(NoteMapper::toDomainList)
    }

    override fun observeNote(id: Long): Flow<Note?> {
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
