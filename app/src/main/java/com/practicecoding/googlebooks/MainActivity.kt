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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "books_search_screen"
                    ){
                        composable("books_search_screen"){

                        }
                        composable(
                            "book_list_screen/{search}",
                            arguments = listOf(
                                navArgument("search"){
                                    type = NavType.StringType
                                }
                            )
                        ){
                            val search = it.arguments?.getString("search")
                        }
                        composable(
                            "book_info_screen/{bookId}",
                            arguments = listOf(
                                navArgument("bookId"){
                                    type = NavType.StringType
                                }
                            )
                        ){
                            val bookId = it.arguments?.getString("bookId")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoogleBooksTopAppBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(title = {
        Text(text = "Google Books App",
            style = MaterialTheme.typography.headlineMedium)
    })
}