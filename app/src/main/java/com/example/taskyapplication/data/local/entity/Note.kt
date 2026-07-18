package com.example.taskyapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val content: String,
    val createAt: Long,
    val updatedAt: Long,
    val isPinned: Boolean,
    val isArchieved: Boolean,
    val color: String?,
    val tags: List<String> = emptyList()
)