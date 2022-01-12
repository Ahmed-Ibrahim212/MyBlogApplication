package com.olamachia.simpleblogapp.di

import networkModule

val appComponent = listOf(
    cacheModule,
    networkModule,
    viewModelsModule,
    repositoryModule
)