package com.example.data.repository

import android.content.Context
import com.example.database.di.createNoteDao

internal fun createNotesRepository(context: Context): NotesRepository =
    NotesRepositoryImpl(createNoteDao(context))
