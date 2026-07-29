package com.example.taskyapplication.di

import android.content.Context
import com.example.taskyapplication.data.local.database.NoteDatabase
import com.example.taskyapplication.data.repository.NotesRepositoryImpl
import com.example.taskyapplication.domain.repository.NotesRepository

interface AppContainer {
    val notesRepository: NotesRepository
}

class DefaultAppContainer(
    private val context: Context
) : AppContainer {

    override val notesRepository: NotesRepository by lazy {
        NotesRepositoryImpl(
            NoteDatabase.getDataBase(context).noteDao()
        )
    }
}
