package com.example.taskyapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.taskyapplication.data.local.entity.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)
    @Update
    suspend fun update(note: Note)
    @Delete
    suspend fun delete(note: Note)
    @Query("SELECT * from notes WHERE id = :id")
    fun getItem(id: String): Flow<Note>
    @Query("SELECT * from notes ORDER BY createAt DESC")
    fun getAllItems(): Flow<Flow<Note>>
}
