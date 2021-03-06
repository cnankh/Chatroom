package com.example.chatroom.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.chatroom.R
import com.example.chatroom.databinding.FragmentLoginBinding
import com.example.chatroom.view.fragments.navbar.BottomNavController
import com.example.chatroom.controller.LoginController
import com.example.chatroom.viewmodel.LoginViewModel


class LoginFragment : Fragment(), LoginController {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialConfiguration()
        observer()
    }

    private fun observer() {
        viewModel.loading.observe(this, Observer {
            binding.loading.visibility = if (it) View.VISIBLE else View.GONE
            if (!it) {
                binding.loginBtn.visibility = View.VISIBLE
            }
        })
    }

    private fun isInputValid(): Boolean {

        if (binding.usernameEt.text.isNullOrBlank()) {
            binding.usernameLayout.error = this.resources.getString(R.string.enter_username)
            return false
        } else {
            binding.usernameLayout.error = null
        }

        if (binding.passwordEt.text.isNullOrBlank()) {
            binding.passwordLayout.error = this.resources.getString(R.string.enter_password)
            return false
        } else {
            binding.usernameLayout.error = null
        }
        return true
    }

    private fun initialConfiguration() {
        BottomNavController.setVisibility(activity!!, false)
        binding.apply {
            lifecycleOwner = this@LoginFragment
            binding.login = viewModel
            binding.controller = this@LoginFragment
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onLoginClicked(view: View) {
        Log.d("tag", "on login clicked")
        if (isInputValid()) {
            viewModel.login(view)
            binding.loginBtn.visibility = View.GONE
        }

    }

    override fun onSignUpClicked(view: View) {
        val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
        Navigation.findNavController(view).navigate(action)
    }
}