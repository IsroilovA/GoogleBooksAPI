package com.practicecoding.googlebooks.network

import com.practicecoding.googlebooks.model.BookInfo
import com.practicecoding.googlebooks.model.Books
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GoogleBooksApiService {
    @GET
    suspend fun getBooksList(@Query("q") q:String): Books

    @GET("{bookId}")
    suspend fun getBookInfo(
        @Path("bookId") bookId:Int
    ): BookInfo
}