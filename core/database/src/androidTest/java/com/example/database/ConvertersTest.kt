package com.example.database

import org.junit.Assert.assertEquals
import org.junit.Test

internal class ConvertersTest {

    private val converters = Converters()

    @Test
    fun tagListsSurviveConversionToJsonAndBack() {
        val cases = listOf(
            emptyList(),
            listOf("one"),
            listOf("two words", "quote: \"Room\"", "кириллица", "emoji 🚀"),
        )

        cases.forEach { tags ->
            assertEquals(tags, converters.toTags(converters.fromTags(tags)))
        }
    }
}
