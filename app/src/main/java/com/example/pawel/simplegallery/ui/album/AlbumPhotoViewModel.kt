package com.example.pawel.simplegallery.ui.album

import android.view.View
import com.example.pawel.simplegallery.api.Photo
import com.example.pawel.simplegallery.tools.Navigator

class AlbumPhotoViewModel(val photo: Photo) {

    fun showPhoto(view: View) {
        Navigator.photo(view.context, photo.id)
    }

}