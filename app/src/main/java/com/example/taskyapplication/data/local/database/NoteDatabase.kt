package com.example.taskyapplication.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskyapplication.data.local.dao.NoteDao
import com.example.taskyapplication.data.local.entity.Note
import kotlin.jvm.java

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var Instance: NoteDatabase? = null
        fun getDataBase(context: Context): NoteDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, NoteDatabase::class.java, "note_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}