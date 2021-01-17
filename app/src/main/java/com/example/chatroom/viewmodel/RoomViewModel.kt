package com.example.chatroom.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.chatroom.model.Room
import com.example.marketmock.viewmodel.BaseViewModel

class RoomViewModel(application: Application) : BaseViewModel(application) {

    val rooms = MutableLiveData<List<Room>>()

    fun refresh() {
        val room = Room("room one")
        val room2 = Room("room two")

        val list = arrayListOf<Room>(room, room2)
        rooms.value = list
    }

}