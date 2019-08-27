package com.example.chocotest

import android.app.Application
import com.example.chocotest.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initDebug()

        // start koin here
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule)
        }

    }

    private fun initDebug() {
        Timber.plant(Timber.DebugTree())
    }
}