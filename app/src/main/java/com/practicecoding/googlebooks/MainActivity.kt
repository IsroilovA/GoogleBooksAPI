package com.practicecoding.googlebooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practicecoding.googlebooks.ui.screens.AppBarTop
import com.practicecoding.googlebooks.ui.screens.BookInfoScreen
import com.practicecoding.googlebooks.ui.screens.BookListScreen
import com.practicecoding.googlebooks.ui.screens.BooksSearchScreen
import com.practicecoding.googlebooks.ui.screens.GoogleBooksViewModel
import com.practicecoding.googlebooks.ui.theme.GoogleBooksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoogleBooksTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val googleBooksViewModel: GoogleBooksViewModel =
                        viewModel(factory = GoogleBooksViewModel.Factory)
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "books_search_screen"
                    ){
                        composable("books_search_screen"){
                            BooksSearchScreen(navController = navController, googleBooksViewModel = googleBooksViewModel, )
                        }
                        composable(
                            "book_list_screen",
                        ){
                            BookListScreen(navController= navController, bookListUiState = googleBooksViewModel.bookListUiState, googleBooksViewModel = googleBooksViewModel)//, retryAction = googleBooksViewModel.getBooksList(googleBooksViewModel.search))
                        }
                        composable(
                            "book_info_screen"
                            ){
                            BookInfoScreen(bookInfoUiState = googleBooksViewModel.bookInfoUiState, googleBooksViewModel = googleBooksViewModel, navController = navController)
                        }
                    }
                }
            }
        }
    }
}