package com.practicecoding.googlebooks.util

sealed interface BookInfoUiState{
    data class Success(val book: Item): BookInfoUiState
    object Loading: BookInfoUiState
    object Error: BookInfoUiState
}