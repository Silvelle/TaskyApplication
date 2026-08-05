package com.example.data

import com.example.data.mapper.NoteMapper
import com.example.database.entity.NoteEntity
import com.example.model.data.Note
import org.junit.Assert.assertEquals
import org.junit.Test

class NoteMapperTest {

    @Test
    fun toEntityMapsEveryField() {
        val domain = testNote(
            id = 42,
            color = "blue",
            tags = listOf("gradle", "room"),
        )
        val expected = testEntity(
            id = 42,
            color = "blue",
            tags = listOf("gradle", "room"),
        )

        assertEquals(expected, NoteMapper.toEntity(domain))
    }

    @Test
    fun toDomainMapsEveryFieldIncludingNullColorAndEmptyTags() {
        val entity = testEntity(id = 7, color = null, tags = emptyList())
        val expected = testNote(id = 7, color = null, tags = emptyList())

        assertEquals(expected, NoteMapper.toDomain(entity))
    }


    @Test
    fun mappingToEntityAndBackPreservesData() {
        val note = testNote(id = 42, color = null, tags = emptyList())

        val mappedNote = NoteMapper.toDomain(NoteMapper.toEntity(note))

        assertEquals(note, mappedNote)
    }

    @Test
    fun toDomainListPreservesInputOrder() {
        val entities = listOf(
            testEntity(id = 3),
            testEntity(id = 1),
            testEntity(id = 2),
        )

        assertEquals(
            listOf(testNote(id = 3), testNote(id = 1), testNote(id = 2)),
            NoteMapper.toDomainList(entities),
        )
    }
}

private fun testNote(
    id: Long,
    color: String? = "green",
    tags: List<String> = listOf("mapping"),
) = Note(
    id = id,
    title = "Title $id",
    content = "Content $id",
    createdAt = 100L,
    updatedAt = 200L,
    isPinned = true,
    isArchieved = false,
    color = color,
    tags = tags,
)

private fun testEntity(
    id: Long,
    color: String? = "green",
    tags: List<String> = listOf("mapping"),
) = NoteEntity(
    id = id,
    title = "Title $id",
    content = "Content $id",
    createdAt = 100L,
    updatedAt = 200L,
    isPinned = true,
    isArchieved = false,
    color = color,
    tags = tags,
)
