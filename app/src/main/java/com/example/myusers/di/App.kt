package com.example.myusers.di

import android.app.Application
import com.example.myusers.di.modules.apiModule
import com.example.myusers.di.modules.appModule
import com.example.myusers.di.modules.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(dataModule, appModule, apiModule))
        }

    }
}