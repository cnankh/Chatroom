package com.example.chatroom.model

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("access_token")
    val token: String
)