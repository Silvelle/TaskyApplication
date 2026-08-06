package com.example.domain.usecase

import com.example.data.repository.NotesRepository
import com.example.model.data.Note
import com.example.model.data.NoteDocument
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetNotesUseCaseTest {

    @Test
    fun returnsCurrentNotesFromRepository() = runTest {
        val expected = listOf(testNote(id = 1), testNote(id = 2))
        val repository = FakeNotesRepository(expected)
        val useCase = GetNotesUseCase(repository)

        assertEquals(expected, useCase().first())
    }

    @Test
    fun returnsEmptyListWhenRepositoryIsEmpty() = runTest {
        val useCase = GetNotesUseCase(FakeNotesRepository())

        assertEquals(emptyList<Note>(), useCase().first())
    }

    @Test
    fun forwardsLaterRepositoryEmissions() = runTest {
        val repository = FakeNotesRepository()
        val useCase = GetNotesUseCase(repository)
        val emissions = mutableListOf<List<Note>>()
        val collection = launch {
            useCase().take(2).toList(emissions)
        }
        runCurrent()

        val updated = listOf(testNote(id = 3))
        repository.setNotes(updated)
        runCurrent()
        collection.join()

        assertEquals(listOf(emptyList(), updated), emissions)
    }
}

private class FakeNotesRepository(
    notes: List<Note> = emptyList(),
) : NotesRepository {
    private val notesFlow = MutableStateFlow(notes)

    fun setNotes(notes: List<Note>) {
        notesFlow.value = notes
    }

    override fun observeNotes(): Flow<List<Note>> = notesFlow

    override fun observeNote(id: Long): Flow<Note?> =
        MutableStateFlow(notesFlow.value.firstOrNull { it.id == id })

    override suspend fun insertNote(note: Note) = Unit

    override suspend fun deleteNote(note: Note) = Unit

    override suspend fun updateNote(note: Note) = Unit
}

private fun testNote(id: Long) = Note(
    id = id,
    title = "Title $id",
    document = NoteDocument(text = "Content $id"),
    createdAt = 100 + id,
    updatedAt = 200 + id,
    isPinned = false,
    isArchieved = false,
)
