package com.example.data.di

import com.example.data.repository.NotesRepository
import com.example.data.repository.NotesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {
    @Binds
    abstract fun bindNotesRepository(
      implementation:
      NotesRepositoryImpl
    ): NotesRepository
}