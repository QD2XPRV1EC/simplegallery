package com.example.pawel.simplegallery.ui.profile

import android.view.View
import com.example.pawel.simplegallery.api.Album
import com.example.pawel.simplegallery.tools.Navigator

class ProfileAlbumViewModel(val album: Album) {

    fun showAlbum(view: View) {
        Navigator.album(view.context, album.id)
    }
}