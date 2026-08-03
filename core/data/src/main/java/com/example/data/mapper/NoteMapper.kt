package com.example.data.mapper

import com.example.database.entity.NoteEntity
import com.example.model.data.Note

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
            color = entity.color,
            tags = entity.tags,
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
            tags = note.tags,
        )
    }

    fun toDomainList(entities: List<NoteEntity>): List<Note> {
        return entities.map(::toDomain)
    }
}
