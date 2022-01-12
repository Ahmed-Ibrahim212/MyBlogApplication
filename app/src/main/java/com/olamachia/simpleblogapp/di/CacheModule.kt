package com.olamachia.simpleblogapp.di

import androidx.room.Room
import com.olamachia.simpleblogapp.R
import com.olamachia.simpleblogapp.cache.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val cacheModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            androidApplication().baseContext.getString(R.string.app_name)
        ).build()
    }

    single {
        get<AppDatabase>().cacheDao()
    }
}
