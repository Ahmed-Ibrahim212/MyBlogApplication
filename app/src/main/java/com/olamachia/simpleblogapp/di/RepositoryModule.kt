package com.olamachia.simpleblogapp.di

import com.olamachia.simpleblogapp.cache.CommentCacheMapper
import com.olamachia.simpleblogapp.cache.PostCacheMapper
import com.olamachia.simpleblogapp.model.domain.MainRepository
import com.olamachia.simpleblogapp.model.remote.CommentNetworkMapper
import com.olamachia.simpleblogapp.model.remote.PostNetworkMapper
import io.reactivex.schedulers.Schedulers.single
import okhttp3.internal.platform.android.AndroidSocketAdapter.Companion.factory
import org.koin.dsl.module


val repositoryModule = module {

    factory { PostCacheMapper() }
    factory { PostNetworkMapper() }
    factory { CommentNetworkMapper() }
    factory { CommentCacheMapper() }

    single { MainRepository(
        cacheDao = get(),
        postService = get(),
        postCacheMapper = get(),
        postNetworkMapper = get(),
        commentCacheMapper = get(),
        commentNetworkMapper = get())
    }
}
