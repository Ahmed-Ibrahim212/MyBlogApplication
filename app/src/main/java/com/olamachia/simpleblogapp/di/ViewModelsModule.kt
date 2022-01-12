package com.olamachia.simpleblogapp.di

import com.olamachia.simpleblogapp.viewmodels.BlogPostsViewModel
import com.olamachia.simpleblogapp.viewmodels.PostDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { BlogPostsViewModel(get()) }
    viewModel { PostDetailViewModel(get()) }
}
