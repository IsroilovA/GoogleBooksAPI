package com.practicecoding.googlebooks.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.practicecoding.googlebooks.util.SearchBooksEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksSearchScreen(navController: NavController, googleBooksViewModel: GoogleBooksViewModel) {

    Row {
        TextField(
            value = googleBooksViewModel.search,
            onValueChange = {googleBooksViewModel.onEvent(SearchBooksEvent.SetSearch(it))},
            label = {Text("Search")},
        )

        Button(onClick = {
            googleBooksViewModel.onEvent(SearchBooksEvent.OnSearchBookClick(navController))
        }) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        }
    }

}