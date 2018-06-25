package com.example.pawel.simplegallery.ui.users

import android.arch.lifecycle.LiveDataReactiveStreams
import android.support.v4.widget.SwipeRefreshLayout
import com.example.pawel.simplegallery.R
import com.example.pawel.simplegallery.api.User
import com.example.pawel.simplegallery.databinding.ItemUserBinding
import com.example.pawel.simplegallery.repository.UsersRepository
import com.example.pawel.simplegallery.tools.LiveDataRecycleViewAdapter
import com.example.pawel.simplegallery.tools.LiveDataViewModel
import javax.inject.Inject


class UsersViewModel @Inject constructor(private val usersRepository: UsersRepository) : LiveDataViewModel() {

    var networkError = LiveDataReactiveStreams.fromPublisher(usersRepository.getNetworkError())
    var users = LiveDataReactiveStreams.fromPublisher(usersRepository.getUsers(compositeDisposable))

    val adapter = LiveDataRecycleViewAdapter<ItemUserBinding, User>(
            users,
            R.layout.item_user
    ) {
        this.model = UsersItemViewModel(it)
    }

    val refresh: (SwipeRefreshLayout) -> Unit = { view ->
        usersRepository.loadUsers(compositeDisposable) {
            view.isRefreshing = false
        }
    }
}