package com.example.heybooks.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class BookItem(
    val authors: List<String> = emptyList(),
    val categories: List<String> = emptyList(),
    @Transient val isbn: String = "",
    @Transient val longDescription: String = "",
    @Transient val pageCount: Int = 0,
    @Transient val publishedDate: String = "",
    @Transient val shortDescription: String = "",
    val status: String = "",
    val thumbnailUrl: String = "",
    val title: String = ""
)