package com.example.allergenfinder.presentation.app

import android.app.Application
import com.example.allergenfinder.di.libraryModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AllergenFinderApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AllergenFinderApplication)
            modules(libraryModules)
        }
    }
}
