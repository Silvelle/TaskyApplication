package com.example.database

import com.example.database.entity.NoteEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

internal class NoteDaoTest : DatabaseTest() {
    @Test
    fun insertedNoteCanBeObserved() = runTest {
        val note = NoteEntity(
            id = 1,
            title = "Title",
            content = "Content",
            createdAt = 100,
            updatedAt = 100,
            isPinned = false,
            isArchieved = false,
            color = null,
            tags = listOf("room", "test"),
        )

        noteDao.insert(note)

        assertEquals(note, noteDao.getNote(note.id).first())
    }
}
