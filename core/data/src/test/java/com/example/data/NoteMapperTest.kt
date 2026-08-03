package com.example.data

import com.example.data.mapper.NoteMapper
import com.example.model.data.Note
import org.junit.Assert.assertEquals
import org.junit.Test

class NoteMapperTest {
    @Test
    fun `mapping a note to an entity and back preserves its data`() {
        val note = Note(
            id = 42,
            title = "Dependency cleanup",
            content = "Keep module boundaries explicit",
            createdAt = 100,
            updatedAt = 200,
            isPinned = true,
            isArchieved = false,
            color = "blue",
            tags = listOf("gradle", "room"),
        )

        val mappedNote = NoteMapper.toDomain(NoteMapper.toEntity(note))

        assertEquals(note, mappedNote)
    }
}
