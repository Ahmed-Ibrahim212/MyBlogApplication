package com.olamachia.simpleblogapp.cache

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [PostCacheEntity::class, CommentCacheEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "blog_db"
    }
    abstract fun cacheDao() : CacheDao
}