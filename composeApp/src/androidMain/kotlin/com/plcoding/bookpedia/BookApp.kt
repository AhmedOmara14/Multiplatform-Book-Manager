package com.plcoding.bookpedia

import android.app.Application
import com.plcoding.bookpedia.core.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class BookApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BookApp)
            androidLogger()
        }
    }
}