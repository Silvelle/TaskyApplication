package com.example.taskyapplication.di

import android.content.Context
import com.example.data.repository.NotesRepository
import com.example.data.repository.createNotesRepository

interface AppContainer {
    val notesRepository: NotesRepository
}

class DefaultAppContainer(
    private val context: Context
) : AppContainer {

    override val notesRepository: NotesRepository by lazy {
        createNotesRepository(context)
    }
}
