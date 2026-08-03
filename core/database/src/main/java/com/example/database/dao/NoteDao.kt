package com.example.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.database.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(noteEntity: NoteEntity)
    @Update
    suspend fun update(noteEntity: NoteEntity)
    @Delete
    suspend fun delete(noteEntity: NoteEntity)
    @Query("SELECT * from notes WHERE id = :id")
    fun getNote(id: Long): Flow<NoteEntity?>
    @Query("SELECT * from notes ORDER BY createdAt DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>
}
