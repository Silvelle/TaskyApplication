package com.example.feature.note.api

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object Notes : NavKey

@Serializable
data class NoteDetail(
    val noteId: Long?,
) : NavKey
