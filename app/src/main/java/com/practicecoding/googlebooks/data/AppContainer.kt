package com.practicecoding.googlebooks.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.practicecoding.googlebooks.network.GoogleBooksApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val googleBooksRepository: GoogleBooksRepository
}

class DefaultAppContainer: AppContainer{
    private val baseUrl = "https://www.googleapis.com/books/v1/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json{ignoreUnknownKeys = true}.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: GoogleBooksApiService by lazy {
        retrofit.create(GoogleBooksApiService::class.java)
    }

    override val googleBooksRepository: GoogleBooksRepository by lazy {
        NetworkGoogleBooksRepository(retrofitService)
    }
}