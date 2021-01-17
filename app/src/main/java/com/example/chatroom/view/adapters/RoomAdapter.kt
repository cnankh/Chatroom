package com.example.chatroom.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

import com.example.chatroom.R
import com.example.chatroom.databinding.ItemRoomBinding
import com.example.chatroom.model.Room
import com.example.chatroom.viewmodel.RoomController

class RoomAdapter(val dataSet: ArrayList<Room>) :
    RecyclerView.Adapter<RoomAdapter.CustomViewHolder>(), RoomController {

    fun updateList(newList: List<Room>) {
        dataSet.clear()
        dataSet.addAll(newList)
        notifyDataSetChanged()

    }

    class CustomViewHolder(var view: ItemRoomBinding) :
        RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemRoomBinding>(
                inflater,
                R.layout.item_room,
                parent,
                false
            )

        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.room = dataSet[position]
        holder.view.controller = this
    }

    override fun getItemCount() = dataSet.size

    override fun onRoomClicked(view: View) {
        Log.d("room adapter", "clicked")
    }

}