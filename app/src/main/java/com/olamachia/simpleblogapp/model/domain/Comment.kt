package com.olamachia.simpleblogapp.model.domain

data class Comment (
        val postId: Int,
        val id: Int,
        val name: String,
        val email: String,
        val body: String
)

