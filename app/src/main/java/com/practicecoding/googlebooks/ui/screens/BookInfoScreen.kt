package com.practicecoding.googlebooks.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.practicecoding.googlebooks.util.BookInfoUiState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.practicecoding.googlebooks.R
import com.practicecoding.googlebooks.model.BookInfo
import com.practicecoding.googlebooks.util.UiEvents

@Composable
fun BookInfoScreen(
    bookInfoUiState: BookInfoUiState,
    modifier: Modifier = Modifier,
    googleBooksViewModel: GoogleBooksViewModel,
    navController: NavController
) {
    when(bookInfoUiState){
        BookInfoUiState.Error -> {
            ErrorScreen(modifier = Modifier.fillMaxSize())
        }
        BookInfoUiState.Loading -> {
            LoadingScreen(modifier = Modifier.fillMaxSize())
        }
        is BookInfoUiState.Success -> {
            BookInformation(bookInfo = bookInfoUiState.book, googleBooksViewModel = googleBooksViewModel, navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookInformation(
    bookInfo: BookInfo,
    modifier: Modifier = Modifier,
    googleBooksViewModel: GoogleBooksViewModel,
    navController: NavController
) {
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = { googleBooksViewModel.onUiEvent(UiEvents.PopBackStack(navController)) }, modifier = Modifier.size(70.dp)) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back button")
            }
        },
        topBar = { AppBarTop() }
    ) {
        Column(
            modifier = Modifier.padding(6.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(bookInfo.info.imageLinks.medium.replace("http", "https"))
                    .build(),
                contentDescription = "Book image",
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = bookInfo.info.title,
                style = MaterialTheme.typography.titleLarge
            )
            bookInfo.info.authors.forEach { author -> 
                Text(
                    text = "Written by ${author}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "Language: ${bookInfo.info.language}",
                style = MaterialTheme.typography.bodyLarge,
    
            )
            Text(
                text = "Published date: ${bookInfo.info.publishedDate}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Page count: " + bookInfo.info.pageCount.toString(),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

