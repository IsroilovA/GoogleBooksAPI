package com.practicecoding.googlebooks

import android.app.Application
import com.practicecoding.googlebooks.data.AppContainer
import com.practicecoding.googlebooks.data.DefaultAppContainer

class GoogleBooksApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}