package com.practicecoding.googlebooks.util

import com.practicecoding.googlebooks.model.Books

sealed interface BookListUiState{
    data class Success(val books: Books): BookListUiState
    object Loading: BookListUiState
    object Error: BookListUiState
}