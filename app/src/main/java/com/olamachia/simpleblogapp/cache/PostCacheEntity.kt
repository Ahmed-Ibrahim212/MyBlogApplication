package com.olamachia.simpleblogapp.cache

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class PostCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "userId")
    var userId: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "body")
    var body: String
)
