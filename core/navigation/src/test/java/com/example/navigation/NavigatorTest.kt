package com.example.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

private data object Home : NavKey
private data object Notes : NavKey
private data object Tasks : NavKey
private data object Calendar : NavKey
private data object Profile : NavKey
private data class NoteDetail(val noteId: Long?) : NavKey

class NavigatorTest {

    private lateinit var state: NavigationState
    private lateinit var navigator: Navigator

    // Set up the state and navigator before each test
    @Before
    fun setUp() {
        state = NavigationState(
            StartRoute = Home,
            topLevelRoute = NavBackStack(Home),
            subStacks = mapOf(
                Home to NavBackStack(Home),
                Notes to NavBackStack(Notes),
                Tasks to NavBackStack(Tasks),
                Calendar to NavBackStack(Calendar),
                Profile to NavBackStack(Profile),
            )
        )
        navigator = Navigator(state)
    }

    @Test
    fun `initial state is Home`() {
        Assert.assertEquals(Home, state.currentKey)
    }

    @Test
    fun `navigating to Notes changes current key`() {
        navigator.navigate(Notes)
        Assert.assertEquals(Notes, state.currentKey)
    }

    @Test
    fun `navigating to Notes changes current top destination stack`() {
        navigator.navigate(Notes)
        Assert.assertEquals(Notes, state.topLevelRoute.last())
    }

    @Test
    fun `navigating to Notes changes current substack`() {
        navigator.navigate(Notes)
        Assert.assertEquals(Notes, state.currentSubStack.last())
    }

    @Test
    fun `navigating to note detail adds it to Notes stack`() {
        navigator.navigate(Notes)
        navigator.navigate(NoteDetail(noteId = 42L))
        Assert.assertEquals(NoteDetail(42L), state.currentKey)
        Assert.assertEquals(
            listOf(Notes, NoteDetail(42L)),
            state.currentSubStack.toList(),
        )
    }

    @Test
    fun `navigating to Notes twice clears the substack`() {
        navigator.navigate(Notes)
        navigator.navigate(NoteDetail(noteId = 42L))

        navigator.navigate(Notes)

        Assert.assertEquals(listOf(Notes), state.currentSubStack.toList())
        Assert.assertEquals(Notes, state.currentKey)
    }

    @Test
    fun `back from note details returns to Notes`() {
        navigator.navigate(Notes)
        navigator.navigate(NoteDetail(noteId = 42L))
        navigator.goBack()
        Assert.assertEquals(Notes, state.currentKey)
    }

    @Test
    fun `back from one Top level destination to other Top level destination`() {
        navigator.navigate(Tasks)
        navigator.navigate(Notes)
        navigator.goBack()
        Assert.assertEquals(Tasks, state.currentKey)
    }

    @Test
    fun `back from tp level destination to other top level destination doesn't clear substack`() {
        navigator.navigate(Notes)
        navigator.navigate(NoteDetail(42L))
        navigator.navigate(Tasks)
        navigator.goBack()
        Assert.assertEquals(listOf(Notes, NoteDetail(42L)), state.currentSubStack.toList())
    }

    @Test
    fun `revisit a toplevel tab`() {
        navigator.navigate(Notes)
        navigator.navigate(Tasks)
        navigator.navigate(Notes)
        Assert.assertEquals(
            listOf(Home, Tasks, Notes),
            state.topLevelRoute.toList()
        )
        Assert.assertEquals(Notes, state.currentTopLevelKey)
    }

    @Test
    fun `open the same note twice does not duplicate it`() {
        val note = NoteDetail(1L)

        navigator.navigate(Notes)
        navigator.navigate(note)
        navigator.navigate(note)

        Assert.assertEquals(
            listOf(Notes, note),
            state.currentSubStack.toList(),
        )
        Assert.assertEquals(
            1,
            state.currentSubStack.count { it == note },
        )
    }

    @Test
    fun `back from second note returns to first note`() {
        val firstNote = NoteDetail(noteId = 1L)
        val secondNote = NoteDetail(noteId = 2L)

        navigator.navigate(Notes)
        navigator.navigate(firstNote)
        navigator.navigate(secondNote)

        navigator.goBack()

        Assert.assertEquals(firstNote, state.currentKey)
        Assert.assertEquals(
            listOf(Notes, firstNote),
            state.currentSubStack.toList(),
        )
    }


    private fun assertNavigationStateIsValid() {
        // There must always be a current top-level destination
        Assert.assertTrue(state.topLevelRoute.isNotEmpty())

        // Start route must have its own stack
        Assert.assertTrue(state.StartRoute in state.subStacks)

        // Every visited top-level key must have a stack
        state.topLevelRoute.forEach { key ->
            Assert.assertTrue(key in state.subStacks)
        }

        // Every sub-stack must contain its top-level key at index 0
        state.subStacks.forEach { (topLevelKey, stack) ->
            Assert.assertTrue(stack.isNotEmpty())
            assertEquals(topLevelKey, stack.first())
        }

        // The currently selected stack must remain non-empty
        Assert.assertTrue(state.currentSubStack.isNotEmpty())
    }

    @Test
    fun `navigation operations preserve a valid state`() {
        assertNavigationStateIsValid()

        navigator.navigate(Notes)
        assertNavigationStateIsValid()

        navigator.navigate(NoteDetail(1L))
        assertNavigationStateIsValid()

        navigator.goBack()
        assertNavigationStateIsValid()

        navigator.navigate(Tasks)
        assertNavigationStateIsValid()

        navigator.navigate(Home)
        assertNavigationStateIsValid()
    }
}
