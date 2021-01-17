package com.example.chatroom.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chatroom.R
import com.example.chatroom.databinding.ItemMessageRecievedBinding
import com.example.chatroom.databinding.ItemRoomBinding
import com.example.chatroom.model.Message
import com.example.chatroom.model.Room
import com.example.chatroom.viewmodel.RoomController

class MessageAdapter(val dataSet: ArrayList<Message>) :
    RecyclerView.Adapter<MessageAdapter.CustomViewHolder>() {

    fun updateList(newList: List<Message>) {
        dataSet.clear()
        dataSet.addAll(newList)
        notifyDataSetChanged()

    }

    class CustomViewHolder(var view: ItemMessageRecievedBinding) :
        RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemMessageRecievedBinding>(
                inflater,
                R.layout.item_message_recieved,
                parent,
                false
            )

        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.message = dataSet[position]
    }

    override fun getItemCount() = dataSet.size


}