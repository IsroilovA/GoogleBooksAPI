package com.practicecoding.googlebooks.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.practicecoding.googlebooks.R

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarTop(){
    CenterAlignedTopAppBar(
        title = { Text(text = "Google Books", style = MaterialTheme.typography.headlineLarge)},
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.LightGray)
    )
}