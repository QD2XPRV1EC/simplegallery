package com.example.pawel.simplegallery.ui.photo

import android.arch.lifecycle.LiveDataReactiveStreams
import com.example.pawel.simplegallery.repository.PhotosRepository
import com.example.pawel.simplegallery.tools.LiveDataViewModel
import javax.inject.Inject

class PhotoViewModel @Inject constructor(id:Int, photosRepository: PhotosRepository) : LiveDataViewModel() {

    var networkError = LiveDataReactiveStreams.fromPublisher(photosRepository.getNetworkError())
    val photo = LiveDataReactiveStreams.fromPublisher(photosRepository.getById(compositeDisposable, id))

}