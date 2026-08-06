package com.example.feature.note.impl.presentation

import com.example.data.repository.NotesRepository
import com.example.domain.usecase.GetNotesUseCase
import com.example.model.data.Note
import com.example.model.data.NoteDocument
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class NotesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun startsLoadingAndStopsAfterFirstRepositoryEmission() = runTest {
        val repository = FakeNotesRepository()
        val viewModel = createViewModel(repository)

        assertTrue(viewModel.uiState.value.isLoading)

        runCurrent()

        assertFalse(viewModel.uiState.value.isLoading)
        assertEquals(emptyList<Note>(), viewModel.uiState.value.notesList)
    }

    @Test
    fun initialRepositoryNotesAreExposedInUiState() = runTest {
        val expected = listOf(testNote(id = 1), testNote(id = 2))
        val repository = FakeNotesRepository(expected)
        val viewModel = createViewModel(repository)

        advanceUntilIdle()

        assertEquals(expected, viewModel.uiState.value.notesList)
        assertFalse(viewModel.uiState.value.isLoading)
    }

    @Test
    fun laterRepositoryEmissionsUpdateUiState() = runTest {
        val repository = FakeNotesRepository()
        val viewModel = createViewModel(repository)
        advanceUntilIdle()

        val expected = listOf(testNote(id = 3))
        repository.setNotes(expected)
        advanceUntilIdle()

        assertEquals(expected, viewModel.uiState.value.notesList)
    }

    @Test
    fun addNoteUpdatesUiStateThroughRepository() = runTest {
        val repository = FakeNotesRepository()
        val viewModel = createViewModel(repository)
        advanceUntilIdle()
        val note = testNote(id = 1)

        viewModel.addNote(note)
        advanceUntilIdle()

        assertEquals(listOf(note), viewModel.uiState.value.notesList)
    }

    @Test
    fun deleteNoteUpdatesUiStateThroughRepository() = runTest {
        val noteToDelete = testNote(id = 1)
        val noteToKeep = testNote(id = 2)
        val repository = FakeNotesRepository(listOf(noteToDelete, noteToKeep))
        val viewModel = createViewModel(repository)
        advanceUntilIdle()

        viewModel.deleteNote(noteToDelete)
        advanceUntilIdle()

        assertEquals(listOf(noteToKeep), viewModel.uiState.value.notesList)
    }

    private fun createViewModel(repository: NotesRepository) = NotesViewModel(
        notesRepository = repository,
        getNotes = GetNotesUseCase(repository),
    )
}

private class FakeNotesRepository(
    initialNotes: List<Note> = emptyList(),
) : NotesRepository {
    private val notes = MutableStateFlow(initialNotes)

    fun setNotes(values: List<Note>) {
        notes.value = values
    }

    override fun observeNotes(): Flow<List<Note>> = notes

    override fun observeNote(id: Long): Flow<Note?> =
        notes.map { values -> values.firstOrNull { it.id == id } }

    override suspend fun insertNote(note: Note) {
        notes.update { values ->
            if (values.any { it.id == note.id }) values else values + note
        }
    }

    override suspend fun deleteNote(note: Note) {
        notes.update { values -> values.filterNot { it.id == note.id } }
    }

    override suspend fun updateNote(note: Note) {
        notes.update { values ->
            values.map { stored -> if (stored.id == note.id) note else stored }
        }
    }
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
