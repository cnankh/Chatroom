package com.example.chatroom.controller

import android.view.View

interface ChatController {
    fun onBackClicked(view: View)

    fun onSendClicked(view: View)
}