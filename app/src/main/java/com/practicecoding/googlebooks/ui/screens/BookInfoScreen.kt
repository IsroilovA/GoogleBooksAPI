package com.practicecoding.googlebooks.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import com.practicecoding.googlebooks.util.BookInfoUiState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.practicecoding.googlebooks.R
import com.practicecoding.googlebooks.model.BookInfo

@Composable
fun BookInfoScreen(
    bookInfoUiState: BookInfoUiState,
    modifier: Modifier = Modifier
) {
    when(bookInfoUiState){
        BookInfoUiState.Error -> {
            ErrorScreen(modifier = Modifier.fillMaxSize())
        }
        BookInfoUiState.Loading -> {
            LoadingScreen(modifier = Modifier.fillMaxSize())
        }
        is BookInfoUiState.Success -> {
            BookInformation(bookInfoUiState.book)
        }
    }
}

@Composable
fun BookInformation(
    bookInfo: BookInfo,
    modifier: Modifier = Modifier
) {
    Column(

    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(bookInfo.info.imageLinks.thumbnail.replace("http", "https"))
                .build(),
            contentDescription = "Book image",
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.loading_img),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize(2f)
                .padding(4.dp)
        )
    }
}
