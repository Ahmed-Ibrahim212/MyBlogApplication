package com.olamachia.simpleblogapp.model.domain

data class Post(
    val userId: Int,
    val postId: Int,
    val title: String,
    val body: String
)