package com.example.data

import com.example.data.serialization.NoteDocumentJson
import com.example.model.data.InlineStyle
import com.example.model.data.InlineStyleRange
import com.example.model.data.NoteDocument
import org.junit.Assert.assertEquals
import org.junit.Test

class NoteDocumentSerializationTest {
    @Test
    fun documentCanBeConvertedToJsonAndBack() {
        val original = NoteDocument(
            text = "Hello world",
            inlineStyles = listOf(
                InlineStyleRange(
                    start = 0,
                    end = 5,
                    type = InlineStyle.BOLD
                )
            ),
        )
        val encoded = NoteDocumentJson.encode(original)
        val decoded = NoteDocumentJson.decode(
            value = encoded,
            fallbackText = ""
        )

        assertEquals(original, decoded)
    }

    @Test
    fun blankJsonFallsBackToPlainText() {
        val decoded = NoteDocumentJson.decode(
            value = "",
            fallbackText = "Legacy note",
        )

        assertEquals(NoteDocument(text = "Legacy note"), decoded)
    }

    @Test
    fun malformedJsonFallsBackToPlainText() {
        val decoded = NoteDocumentJson.decode(
            value = "not-json",
            fallbackText = "Readable note",
        )

        assertEquals(NoteDocument(text = "Readable note"), decoded)
    }
}
