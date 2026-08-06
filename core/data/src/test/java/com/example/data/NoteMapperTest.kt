package com.example.data

import com.example.data.mapper.NoteMapper
import com.example.data.serialization.NoteDocumentJson
import com.example.database.entity.NoteEntity
import com.example.model.data.InlineStyle
import com.example.model.data.InlineStyleRange
import com.example.model.data.Note
import com.example.model.data.NoteDocument
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
    document = testDocument(id),
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
    plainText = testDocument(id).text,
    contentJson = NoteDocumentJson.encode(testDocument(id)),
    createdAt = 100L,
    updatedAt = 200L,
    isPinned = true,
    isArchieved = false,
    color = color,
    tags = tags,
)

private fun testDocument(id: Long) = NoteDocument(
    text = "Content $id",
    inlineStyles = listOf(
        InlineStyleRange(
            start = 0,
            end = 7,
            type = InlineStyle.BOLD,
        ),
    ),
)
