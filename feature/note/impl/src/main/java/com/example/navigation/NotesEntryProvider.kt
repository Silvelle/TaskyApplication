package com.example.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.feature.note.api.NoteDetail
import com.example.feature.note.impl.presentation.editor.NoteEditorScreen

fun EntryProviderScope<NavKey>.noteEntry(navigator: Navigator) {
    entry<NoteDetail> { destination ->
        NoteEditorScreen(
            noteId = destination.noteId,
            onBack = navigator::goBack,
        )
    }
}