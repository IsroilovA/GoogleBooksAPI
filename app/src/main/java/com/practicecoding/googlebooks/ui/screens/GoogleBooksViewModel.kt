package com.practicecoding.googlebooks.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.practicecoding.googlebooks.GoogleBooksApplication
import com.practicecoding.googlebooks.data.GoogleBooksRepository
import com.practicecoding.googlebooks.util.BookInfoUiState
import com.practicecoding.googlebooks.util.BookListUiState
import com.practicecoding.googlebooks.util.UiEvents
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class GoogleBooksViewModel(private val googleBooksRepository: GoogleBooksRepository): ViewModel(){
    var bookListUiState: BookListUiState by mutableStateOf(BookListUiState.Loading)
        private set
    var search by mutableStateOf("")
        private set
    var bookInfoUiState: BookInfoUiState by mutableStateOf(BookInfoUiState.Loading)
        private set
    fun getBooksList(search: String){
        viewModelScope.launch{
            bookListUiState = BookListUiState.Loading
            bookListUiState = try {
                BookListUiState.Success(
                    googleBooksRepository.getBooksList(search = search)
                )
            }catch (e: IOException){
                BookListUiState.Error
            }catch (e: HttpException){
                BookListUiState.Error
            }
        }
    }

    fun getBookInfo(id: String){
        viewModelScope.launch {
            bookInfoUiState = BookInfoUiState.Loading
            bookInfoUiState = try {
                BookInfoUiState.Success(
                    googleBooksRepository.getBookInfo(id)
                )
            }catch (e: IOException){
                BookInfoUiState.Error
            }catch (e: HttpException){
                BookInfoUiState.Error
            }
        }
    }

    fun onUiEvent(event: UiEvents){
        when(event){
            is UiEvents.OnBookClick -> {
                getBookInfo(event.bookId)
                event.navController.navigate(
                    "book_info_screen"
                )
            }
            is UiEvents.OnSearchBookClick -> {
                getBooksList(search = search)
                event.navController.navigate(
                    "book_list_screen"
                )
            }
            is UiEvents.SearchButtonCLick -> {
                search = ""
                event.navController.navigate(
                    "books_search_screen"
                )
            }
            is UiEvents.SetSearch -> {
                search = event.value
            }

            is UiEvents.PopBackStack -> {
                event.navController.popBackStack()
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as GoogleBooksApplication)
                val googleBooksRepository = application.container.googleBooksRepository
                GoogleBooksViewModel(googleBooksRepository = googleBooksRepository)
            }
        }
    }
}