package com.olamachia.simpleblogapp

import android.app.Application
import com.olamachia.simpleblogapp.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        configureDI()
    }

    private fun configureDI() = startKoin {
        androidContext(this@BaseApplication)
        modules(appComponent)
    }
}