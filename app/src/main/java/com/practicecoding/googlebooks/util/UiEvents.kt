package com.practicecoding.googlebooks.util

import androidx.navigation.NavController

sealed class UiEvents{
    data class OnSearchBookClick(val navController: NavController): UiEvents()
    data class SetSearch(val value: String): UiEvents()
    data class OnBookClick (val navController: NavController, val bookId: String): UiEvents()
    data class SearchButtonCLick(val navController: NavController): UiEvents()

    data class PopBackStack(val navController: NavController) : UiEvents()
}