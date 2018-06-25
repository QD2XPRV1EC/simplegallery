package com.example.pawel.simplegallery.ui.users

import android.view.View
import com.example.pawel.simplegallery.api.User
import com.example.pawel.simplegallery.tools.Navigator

class UsersItemViewModel(val user: User) {

    fun showProfile(view:View) {
        Navigator.profile(view.context, user.id)
    }

}