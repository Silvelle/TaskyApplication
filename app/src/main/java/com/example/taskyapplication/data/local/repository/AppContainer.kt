package com.example.taskyapplication.data.local.repository

import android.content.Context
import com.example.taskyapplication.data.local.database.NoteDatabase

interface AppContainer {
    val notesRepository: NotesRepository
}

class DefaultAppContainer(
    private val context: Context
) : AppContainer {

    override val notesRepository: NotesRepository by lazy {
        OfflineNotesRepository(
            NoteDatabase.getDataBase(context).noteDao()
        )
    }
}
