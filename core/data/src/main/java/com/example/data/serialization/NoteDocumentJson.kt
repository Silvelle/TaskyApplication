package com.example.data.serialization

import com.example.model.data.NoteDocument
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal object NoteDocumentJson {
    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    fun encode(document: NoteDocument): String =
        json.encodeToString(document)

    fun decode(value: String, fallbackText: String): NoteDocument {
        if (value.isBlank()) {
            return NoteDocument(text = fallbackText)
        }

        return runCatching {
            json.decodeFromString<NoteDocument>(value)
        }.getOrElse {
            NoteDocument(text = fallbackText)
        }
    }
}
