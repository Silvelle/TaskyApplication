package com.example.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val plainText: String,
    val contentJson: String,
    val createdAt: Long,
    val updatedAt: Long,
    val isPinned: Boolean,
    val isArchieved: Boolean,
    val color: String?,
    val tags: List<String> = emptyList()
)
