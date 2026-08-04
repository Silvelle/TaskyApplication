package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.database.dao.NoteDao
import com.example.database.entity.NoteEntity


@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class TaskyDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}