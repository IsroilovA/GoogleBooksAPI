package com.practicecoding.googlebooks.util

import com.practicecoding.googlebooks.model.BookInfo

sealed interface BookInfoUiState{
    data class Success(val book: BookInfo): BookInfoUiState
    object Loading: BookInfoUiState
    object Error: BookInfoUiState
}