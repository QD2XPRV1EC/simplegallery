package com.example.pawel.simplegallery.ui.profile

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.Transformations
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import com.example.pawel.simplegallery.R
import com.example.pawel.simplegallery.api.Album
import com.example.pawel.simplegallery.databinding.ItemAlbumBinding
import com.example.pawel.simplegallery.repository.AlbumsRepository
import com.example.pawel.simplegallery.repository.UsersRepository
import com.example.pawel.simplegallery.tools.LiveDataRecycleViewAdapter
import com.example.pawel.simplegallery.tools.LiveDataViewModel
import com.example.pawel.simplegallery.tools.Navigator
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
        private val id: Int,
        usersRepository: UsersRepository,
        private val albumsRepository: AlbumsRepository
) : LiveDataViewModel() {

    var networkError = LiveDataReactiveStreams.fromPublisher(albumsRepository.getNetworkError())
    val user = LiveDataReactiveStreams.fromPublisher(usersRepository.getUserById(compositeDisposable, id))
    val albums = LiveDataReactiveStreams.fromPublisher(albumsRepository.getListByUserId(compositeDisposable, id))

    val address: LiveData<String> = Transformations.map(user) {
        "${it.address.street}\n${it.address.suite}\n${it.address.zipCode}\n${it.address.city}"
    }

    val albumsAdapter = LiveDataRecycleViewAdapter<ItemAlbumBinding, Album>(
            albums,
            R.layout.item_album
    ) {
        this.model = ProfileAlbumViewModel(it)
    }

    val refresh: (SwipeRefreshLayout) -> Unit = { view ->
        albumsRepository.loadAlbums(compositeDisposable, id) {
            view.isRefreshing = false
        }
    }

    fun openWebsite(view: View) {
        user.value?.let {
            Navigator.openWebsite(view.context, it.website)
        }
    }

    fun sendEmail(view: View) {
        user.value?.let {
            Navigator.sendEmail(view.context, it.email)
        }
    }

    fun phoneCall(view: View) {
        user.value?.let {
            Navigator.phoneCall(view.context, it.phone)
        }
    }

    fun openMap(view: View) {
        user.value?.let {
            Navigator.openMap(view.context, it.address.geo.lat, it.address.geo.lng, it.username)
        }
    }

}