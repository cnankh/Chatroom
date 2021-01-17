package com.example.chatroom.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.chatroom.R
import com.google.android.material.bottomnavigation.BottomNavigationPresenter
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottom_nav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initConfiguration()
        bottom_nav = findViewById<BottomNavigationView>(R.id.bottom_nav)
    }

    private fun initConfiguration() {
        //normal nav bar configuration
        val navController = Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        )
        setUpBottomNavMenu(navController)
    }

    private fun setUpBottomNavMenu(navController: NavController) {
        bottom_nav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottom_nav?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
    }
}