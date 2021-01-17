package com.example.chatroom.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import com.example.chatroom.R
import com.example.chatroom.databinding.FragmentSignUpBinding
import com.example.chatroom.view.fragments.navbar.BottomNavController
import com.example.chatroom.controller.SignUpController
import com.example.chatroom.viewmodel.SignUpViewModel


class SignUpFragment : Fragment(), SignUpController {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentSignUpBinding
    private  val  viewModel: SignUpViewModel by viewModels()

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
        BottomNavController.setVisibility(activity!!, false)
       
        binding.apply {
            this.lifecycleOwner = this@SignUpFragment
            this.signUp = viewModel
            this.controller = this@SignUpFragment
        }
    }

    private fun observer() {
        viewModel.loading.observe(this, Observer {
            binding.loading.visibility = if (it) View.VISIBLE else View.GONE
            if (!it) {
                binding.signUpBtn.visibility = View.VISIBLE
            }
        })
    }

    override fun onSignUpClicked(view: View) {
        viewModel.signUp(view)
        binding.signUpBtn.visibility = View.GONE
    }

}