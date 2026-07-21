package com.example.taskyapplication.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskyapplication.data.local.dao.NoteDao
import com.example.taskyapplication.data.local.entity.NoteEntity
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import org.json.JSONArray
import kotlin.jvm.java

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
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


class Converters {

    @TypeConverter
    fun fromTags(tags: List<String>): String =
        JSONArray(tags).toString()

    @TypeConverter
    fun toTags(value: String): List<String> {
        val array = JSONArray(value)
        return List(array.length()) { index ->
            array.getString(index)
        }
    }
}
