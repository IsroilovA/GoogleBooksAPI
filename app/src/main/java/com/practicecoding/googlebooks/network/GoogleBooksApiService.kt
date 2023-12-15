package com.practicecoding.googlebooks.network

import com.practicecoding.googlebooks.model.BookInfo
import com.practicecoding.googlebooks.model.Books
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GoogleBooksApiService {
    @GET("volumes")
    suspend fun getBooksList(@Query("q") q:String): Books

    @GET("volumes/{bookId}")
    suspend fun getBookInfo(
        @Path("bookId") bookId:String
    ): BookInfo
}