package com.olamachia.simpleblogapp.api

import retrofit2.http.GET
import retrofit2.http.Path

interface PostService{
    @GET("posts/")
    suspend fun fetchPosts(): List<PostRemoteEntity>

    @GET("posts/{id}")
    fun fetchPost(@Path("id") postId: Int): PostRemoteEntity

    @GET("comments/")
    suspend fun fetchComments() : List<CommentNetworkEntity>
}