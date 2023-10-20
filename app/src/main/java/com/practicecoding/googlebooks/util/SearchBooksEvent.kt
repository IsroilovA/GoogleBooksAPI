package com.practicecoding.googlebooks.util

import androidx.navigation.NavController

sealed class SearchBooksEvent{
    data class OnSearchBookClick(val navController: NavController): SearchBooksEvent()
    data class SetSearch(val value: String): SearchBooksEvent()
}