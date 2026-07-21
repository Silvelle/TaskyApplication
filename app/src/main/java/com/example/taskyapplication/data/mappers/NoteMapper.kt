package com.example.taskyapplication.data.mappers

import com.example.taskyapplication.data.local.entity.NoteEntity
import com.example.taskyapplication.domain.model.Note


object NoteMapper {
    fun toDomain(entity: NoteEntity): Note {
        return Note (
            id = entity.id,
            title = entity.title,
            content = entity.content,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt,
            isPinned = entity.isPinned,
            isArchieved = entity.isArchieved,
        )
    }

    fun toEntity(note: Note): NoteEntity {
        return NoteEntity(
            id = note.id,
            title = note.title,
            content = note.content,
            createdAt = note.createdAt,
            updatedAt = note.updatedAt,
            isPinned = note.isPinned,
            isArchieved = note.isArchieved,
            color = note.color,
        )
    }

    fun toDomainList(entities: List<NoteEntity>): List<Note> {
        return entities.map(::toDomain)
    }
}

