package com.example.pawel.simplegallery.ui.album

import android.arch.lifecycle.LiveDataReactiveStreams
import android.support.v4.widget.SwipeRefreshLayout
import com.example.pawel.simplegallery.R
import com.example.pawel.simplegallery.api.Photo
import com.example.pawel.simplegallery.databinding.ItemPhotoBinding
import com.example.pawel.simplegallery.repository.PhotosRepository
import com.example.pawel.simplegallery.tools.LiveDataRecycleViewAdapter
import com.example.pawel.simplegallery.tools.LiveDataViewModel
import javax.inject.Inject

class AlbumViewModel @Inject constructor(private val id:Int, private val photosRepository: PhotosRepository) : LiveDataViewModel() {

    var networkError = LiveDataReactiveStreams.fromPublisher(photosRepository.getNetworkError())
    val photos = LiveDataReactiveStreams.fromPublisher(photosRepository.getListByAlbumId(compositeDisposable, id))

    val refresh: (SwipeRefreshLayout) -> Unit = { view ->
        photosRepository.loadPhotos(compositeDisposable, id) {
            view.isRefreshing = false
        }
    }

    val adapter = LiveDataRecycleViewAdapter<ItemPhotoBinding, Photo>(
            photos,
            R.layout.item_photo
    ) {
        this.model = AlbumPhotoViewModel(it)
    }


}