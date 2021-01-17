package com.example.chatroom.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatroom.databinding.FragmentChatBinding
import com.example.chatroom.view.adapters.MessageAdapter
import com.example.chatroom.view.fragments.navbar.BottomNavController
import com.example.chatroom.controller.ChatController
import com.example.chatroom.viewmodel.MessageViewModel

class ChatFragment : Fragment(), ChatController {

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
        BottomNavController.setVisibility(activity!!, false)
        mLayoutManager = LinearLayoutManager(context)

        binding.apply {
            this.controller = this@ChatFragment
        }

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

    override fun onBackClicked(view: View) {
        val action = ChatFragmentDirections.actionChatFragmentToRoomDestination()
        Navigation.findNavController(view).navigate(action)
    }

    override fun onSendClicked(view: View) {

    }

}