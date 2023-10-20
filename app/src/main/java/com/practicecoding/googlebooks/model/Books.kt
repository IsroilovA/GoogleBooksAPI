package com.practicecoding.googlebooks.model

import kotlinx.serialization.Serializable

@Serializable
data class Books(
    val totalItems: Int,
    val items: List<BooksListItem>
)

@Serializable
data class BooksListItem(
    val id: String,
    val volumeInfo: VolumeInfo
)

@Serializable
data class VolumeInfo(
    val title: String,
    val imageLinks: ImageLinks
)

@Serializable
data class ImageLinks(
    val thumbnail: String
)