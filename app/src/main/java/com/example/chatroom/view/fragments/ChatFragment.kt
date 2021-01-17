package com.example.chatroom.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatroom.R
import com.example.chatroom.databinding.FragmentChatBinding
import com.example.chatroom.view.adapters.MessageAdapter
import com.example.chatroom.view.adapters.RoomAdapter
import com.example.chatroom.viewmodel.MessageViewModel
import com.example.chatroom.viewmodel.RoomViewModel

class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MessageViewModel by viewModels()
    private var mAdapter = MessageAdapter(arrayListOf())
    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialConfiguration()
        observer()
    }

    private fun initialConfiguration() {
        mLayoutManager = LinearLayoutManager(context)

        binding.messageRv.apply {
            adapter = mAdapter
            layoutManager = mLayoutManager
        }

        viewModel.refresh()
    }

    private fun observer() {
        viewModel.messages.observe(this, Observer {
            mAdapter.updateList(it)
        })
    }

}