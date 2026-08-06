package com.example.model.data

import kotlinx.serialization.Serializable

@Serializable
data class NoteDocument(
    val version: Int = 1,
    val text: String = "",
    val inlineStyles: List<InlineStyleRange> = emptyList(),
    val paragraphStyles: List<ParagraphStyleRange> = emptyList(),
)

@Serializable
data class InlineStyleRange(
    val start: Int,
    val end: Int,
    val type: InlineStyle,
    val colorArgb: Int? = null
)

@Serializable
enum class InlineStyle {
    BOLD,
    ITALIC,
    HIGHLIGHT,
}

@Serializable
data class ParagraphStyleRange(
    val start: Int,
    val end: Int,
    val type: ParagraphStyle,
)

@Serializable
enum class ParagraphStyle {
    BULLET,
    NUMBERED,
}