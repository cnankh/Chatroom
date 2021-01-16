package com.example.chatroom.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import com.example.chatroom.R
import com.example.chatroom.databinding.FragmentSignUpBinding
import com.example.chatroom.viewmodel.SignUpViewModel
import com.example.marketmock.viewmodel.BaseViewModel


class SignUpFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_sign_up,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        initialConfiguration()
    }

    private fun initialConfiguration() {
        viewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)
        binding.apply {
            this.lifecycleOwner = this@SignUpFragment
            this.signUp = viewModel
        }
    }

    private fun observer() {

    }

}