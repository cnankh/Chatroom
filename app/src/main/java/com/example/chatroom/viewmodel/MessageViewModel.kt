package com.example.chatroom.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.chatroom.model.Message
import com.example.marketmock.viewmodel.BaseViewModel

class MessageViewModel(application: Application) : BaseViewModel(application) {

    val messages = MutableLiveData<List<Message>>()

    fun refresh() {
        val message1 = Message(
            "sina",
            "salam bache ha ch khabar?"
        )

        val message2 = Message(
            "solmaz",
            "salam.mrrrc to khubi?"
        )

        val message3 = Message(
            "Mojtaba",
            "lorem ipsum this is just a test to see how my application works i am the best developer of the town no one can be like me no more"
        )

        val list = arrayListOf<Message>(message1, message2, message3)
        messages.value = list
    }

}