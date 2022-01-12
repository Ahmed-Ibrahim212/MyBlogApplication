package com.olamachia.simpleblogapp.model.remote

import com.google.gson.annotations.SerializedName

data class PostRemoteEntity (

    @SerializedName("userId")
    var userId: Int,

    @SerializedName("id")
    var id: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("body")
    var body: String,
)