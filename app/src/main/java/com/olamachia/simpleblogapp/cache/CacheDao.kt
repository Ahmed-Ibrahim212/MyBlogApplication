package com.olamachia.simpleblogapp.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CacheDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPosts(postEntity: PostCacheEntity) : Long

    @Query("SELECT * FROM posts ")
    fun getPosts() : List<PostCacheEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addComment(commentEntity: CommentCacheEntity) : Long

    @Query("SELECT * FROM comments")
    fun getComments(): List<CommentCacheEntity>

    @Query("SELECT * FROM comments WHERE postId = :postId")
    fun getPostComments(postId: Int) : List<CommentCacheEntity>
}