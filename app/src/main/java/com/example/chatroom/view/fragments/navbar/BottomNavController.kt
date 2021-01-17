package com.example.chatroom.view.fragments.navbar

import android.app.Activity
import android.view.View
import com.example.chatroom.R
import com.google.android.material.bottomnavigation.BottomNavigationView

abstract class BottomNavController {
    companion object {
        fun setVisibility(activity: Activity, visible: Boolean) {
            val bottomNav = activity.findViewById<BottomNavigationView>(R.id.bottom_nav)
            bottomNav.visibility = if (visible) View.VISIBLE else View.GONE
        }
    }
}