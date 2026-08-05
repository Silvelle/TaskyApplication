package com.example.data.repository

import com.example.database.dao.NoteDao
import com.example.database.entity.NoteEntity
import com.example.model.data.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NotesRepositoryImplTest {

    private lateinit var noteDao: FakeNoteDao
    private lateinit var repository: NotesRepository

    @Before
    fun setup() {
        noteDao = FakeNoteDao()
        repository = NotesRepositoryImpl(noteDao)
    }

    @Test
    fun observeNotesMapsEntitiesAndPreservesDaoOrder() = runTest {
        noteDao.setNotes(
            listOf(
                testEntity(id = 3),
                testEntity(id = 1),
                testEntity(id = 2),
            ),
        )

        assertEquals(
            listOf(testNote(id = 3), testNote(id = 1), testNote(id = 2)),
            repository.observeNotes().first(),
        )
    }

    @Test
    fun observeNoteMapsFoundEntity() = runTest {
        noteDao.setNotes(listOf(testEntity(id = 1), testEntity(id = 2)))

        assertEquals(testNote(id = 2), repository.observeNote(id = 2).first())
    }

    @Test
    fun observeNoteReturnsNullForUnknownId() = runTest {
        assertEquals(null, repository.observeNote(id = 404).first())
    }

    @Test
    fun insertConvertsAndStoresDomainNote() = runTest {
        val note = testNote(id = 1, color = null, tags = listOf("new"))

        repository.insertNote(note)

        assertEquals(note, repository.observeNote(note.id).first())
    }

    @Test
    fun updateConvertsAndReplacesMatchingNote() = runTest {
        val original = testNote(id = 1)
        val updated = original.copy(
            title = "Updated",
            isPinned = true,
            tags = listOf("updated"),
        )
        repository.insertNote(original)

        repository.updateNote(updated)

        assertEquals(updated, repository.observeNote(original.id).first())
    }

    @Test
    fun deleteConvertsAndRemovesMatchingNote() = runTest {
        val note = testNote(id = 1)
        repository.insertNote(note)

        repository.deleteNote(note)

        assertEquals(null, repository.observeNote(note.id).first())
    }
}

private class FakeNoteDao : NoteDao {
    private val notes = MutableStateFlow<List<NoteEntity>>(emptyList())

    fun setNotes(values: List<NoteEntity>) {
        notes.value = values
    }

    override suspend fun insert(noteEntity: NoteEntity) {
        notes.update { current ->
            if (current.any { it.id == noteEntity.id }) current else current + noteEntity
        }
    }

    override suspend fun update(noteEntity: NoteEntity) {
        notes.update { current ->
            current.map { stored ->
                if (stored.id == noteEntity.id) noteEntity else stored
            }
        }
    }

    override suspend fun delete(noteEntity: NoteEntity) {
        notes.update { current -> current.filterNot { it.id == noteEntity.id } }
    }

    override fun getNote(id: Long): Flow<NoteEntity?> =
        notes.map { current -> current.firstOrNull { it.id == id } }

    override fun getAllNotes(): Flow<List<NoteEntity>> = notes
}

private fun testNote(
    id: Long,
    color: String? = "green",
    tags: List<String> = listOf("repository"),
) = Note(
    id = id,
    title = "Title $id",
    content = "Content $id",
    createdAt = 100 + id,
    updatedAt = 200 + id,
    isPinned = false,
    isArchieved = false,
    color = color,
    tags = tags,
)

private fun testEntity(
    id: Long,
    color: String? = "green",
    tags: List<String> = listOf("repository"),
) = NoteEntity(
    id = id,
    title = "Title $id",
    content = "Content $id",
    createdAt = 100 + id,
    updatedAt = 200 + id,
    isPinned = false,
    isArchieved = false,
    color = color,
    tags = tags,
)
