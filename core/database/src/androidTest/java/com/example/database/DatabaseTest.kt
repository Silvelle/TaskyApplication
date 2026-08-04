package com.example.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.database.dao.NoteDao
import org.junit.After
import org.junit.Before

internal abstract class DatabaseTest {
    private lateinit var db: TaskyDatabase
    protected lateinit var noteDao: NoteDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(
            context,
            TaskyDatabase::class.java,
        ).build()
        noteDao = db.noteDao()
    }

    @After
    fun teardown() {
        db.close()
    }
}
