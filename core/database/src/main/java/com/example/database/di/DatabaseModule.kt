package com.example.database.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.database.TaskyDatabase
import com.example.database.dao.NoteDao
import com.example.database.entity.NoteEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.json.JSONArray
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(
        @ApplicationContext context: Context,
    ): TaskyDatabase = Room.databaseBuilder(
        context,
        TaskyDatabase::class.java,
        "tasky_database"
    ).build()
}