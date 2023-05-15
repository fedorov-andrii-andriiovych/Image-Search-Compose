package com.fedorov.andrii.andriiovych.imagesearch

import android.app.Application
import com.fedorov.andrii.andriiovych.imagesearch.data.AppContainer

class App : Application() {
        companion object{
            lateinit var container: AppContainer
        }

    override fun onCreate() {
        super.onCreate()
        container = AppContainer()
    }
}