package com.practicecoding.googlebooks.util

sealed interface BookListUiState{
    data class Success(val books: Books): BookListUiState
    object Loading: BookListUiState
    object Error: BookListUiState
}