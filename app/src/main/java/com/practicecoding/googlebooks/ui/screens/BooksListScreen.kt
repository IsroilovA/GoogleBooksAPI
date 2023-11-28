package com.practicecoding.googlebooks.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.practicecoding.googlebooks.R
import com.practicecoding.googlebooks.model.Books
import com.practicecoding.googlebooks.model.BooksListItem
import com.practicecoding.googlebooks.util.BookListUiState

@Composable
fun BookListScreen(
    bookListUiState: BookListUiState,
    //retryAction: (search: String) -> Unit,
    modifier: Modifier = Modifier
) {
    when(bookListUiState){
        BookListUiState.Error -> ErrorScreen(/*retryAction = retryAction(),*/ modifier = Modifier.fillMaxSize())
        BookListUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is BookListUiState.Success -> BookListGrid(books = bookListUiState.books)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = Modifier.size(200.dp),
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = "Loading Content")
}

@Composable
fun ErrorScreen(/*retryAction:() -> Unit,*/ modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = "connection error")
        Text(text = "Failed to load", modifier = Modifier.padding(16.dp))
//        Button(onClick = {
//            retryAction
//        }) {
//            Text(text = "Retry")
//        }
    }
}

@Composable
fun BookListGrid(
    books: Books,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(4.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ){
        items(items = books.items, key = {book -> book.id}){book ->
            BookCard(booksListItem = book,
                modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()
                .aspectRatio(1.5f)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun BookCard(
    booksListItem: BooksListItem,
    modifier: Modifier = Modifier
) {
    Card {
        Column (
            modifier = Modifier.padding(4.dp)
        ){
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(booksListItem.volumeInfo.imageLinks?.thumbnail?.replace("http", "https"))
                    .build(),
                contentDescription = "Book Image",
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize(2f)
                    .padding(4.dp)
            )
            Text(
                text = booksListItem.volumeInfo.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

