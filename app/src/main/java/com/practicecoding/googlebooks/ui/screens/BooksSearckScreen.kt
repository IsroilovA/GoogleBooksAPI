package com.practicecoding.googlebooks.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.practicecoding.googlebooks.util.SearchBooksEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksSearchScreen(navController: NavController, googleBooksViewModel: GoogleBooksViewModel) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        BasicTextField(
            value = googleBooksViewModel.search,
            onValueChange = {
                googleBooksViewModel.onEvent(SearchBooksEvent.SetSearch(it))
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black, fontSize = 20.sp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .height(50.dp)
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
        )
        Button(
            modifier = Modifier.padding(12.dp),
            onClick = {
                googleBooksViewModel.onEvent(SearchBooksEvent.OnSearchBookClick(navController))
            }
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        }
    }

}