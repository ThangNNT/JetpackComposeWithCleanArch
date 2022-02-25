package com.nnt.jetpackcomposewithcleanarch

import android.app.Application
import coil.compose.LocalImageLoader
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}