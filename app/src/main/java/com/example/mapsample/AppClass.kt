package com.example.mapsample

import android.app.Application
import android.content.Context
import com.example.mapsample.di.dataSourceModule
import com.example.mapsample.di.repositoryModule
import com.example.mapsample.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppClass : Application() {

    companion object {
        lateinit var ctx : Context
    }

    override fun onCreate() {
        super.onCreate()
        ctx = this
        startKoin {
            // declare used Android context
            androidContext(this@AppClass)
            // declare modules
            modules(arrayListOf(viewModelModule, repositoryModule, dataSourceModule))
        }
    }
}