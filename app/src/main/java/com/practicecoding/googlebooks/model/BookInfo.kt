package com.practicecoding.googlebooks.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookInfo(
    @SerialName(value = "volumeInfo") val info: Info
)

@Serializable
data class Info(
    val title: String,
    val authors: List<String>,
    val publisher: String,
    val publishedDate: String,
    val description: String,
    val pageCount: Int,
    val language: String
)