package com.example.database.di

import com.example.database.TaskyDatabase
import com.example.database.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DaosModule {
    @Provides
    fun provideNoteDao(database: TaskyDatabase): NoteDao =
        database.noteDao()
}