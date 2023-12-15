package com.practicecoding.googlebooks.util

import androidx.navigation.NavController

sealed class BookListEvent{
    data class OnBookClick (val navController: NavController, val bookId: String): BookListEvent()
}
