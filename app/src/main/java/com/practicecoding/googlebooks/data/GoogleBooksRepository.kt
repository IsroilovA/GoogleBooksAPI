package com.practicecoding.googlebooks.data

import com.practicecoding.googlebooks.model.BookInfo
import com.practicecoding.googlebooks.model.Books
import com.practicecoding.googlebooks.network.GoogleBooksApiService

interface GoogleBooksRepository {
    suspend fun getBooksList(search: String): Books
    suspend fun getBookInfo(id: String): BookInfo
}

class NetworkGoogleBooksRepository(
    private val googleBooksApiService: GoogleBooksApiService
): GoogleBooksRepository{
    override suspend fun getBooksList(search: String): Books = googleBooksApiService.getBooksList(search)
    override suspend fun getBookInfo(id: String): BookInfo = googleBooksApiService.getBookInfo(id)
}