package com.olamachia.simpleblogapp.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CacheDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPosts(postEntity: PostCacheEntity) : Long

    @Query("SELECT * FROM posts")
    suspend fun getPosts() : List<PostCacheEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addComment(commentEntity: CommentCacheEntity) : Long

    @Query("SELECT * FROM comments")
    suspend fun getComments(): List<CommentCacheEntity>

    @Query("SELECT * FROM comments WHERE postId = :postId")
    suspend fun getPostComments(postId: Int) : List<CommentCacheEntity>
}