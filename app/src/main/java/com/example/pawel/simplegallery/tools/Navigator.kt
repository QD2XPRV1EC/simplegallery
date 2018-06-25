package com.example.pawel.simplegallery.tools

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.pawel.simplegallery.ui.album.AlbumActivity
import com.example.pawel.simplegallery.ui.album.AlbumActivity.Companion.ALBUM_ID_TYPE
import com.example.pawel.simplegallery.ui.photo.PhotoActivity
import com.example.pawel.simplegallery.ui.photo.PhotoActivity.Companion.PHOTO_ID_TYPE
import com.example.pawel.simplegallery.ui.profile.ProfileActivity
import com.example.pawel.simplegallery.ui.profile.ProfileActivity.Companion.USER_ID_TYPE

class Navigator {

    companion object {
        fun profile(context: Context, idUser: Int) {
            val intent = Intent(context, ProfileActivity::class.java)
            intent.putExtra(USER_ID_TYPE, idUser)
            context.startActivity(intent)
        }

        fun album(context: Context, idAlbum: Int) {
            val intent = Intent(context, AlbumActivity::class.java)
            intent.putExtra(ALBUM_ID_TYPE, idAlbum)
            context.startActivity(intent)
        }

        fun photo(context: Context, idPhoto: Int) {
            val intent = Intent(context, PhotoActivity::class.java)
            intent.putExtra(PHOTO_ID_TYPE, idPhoto)
            context.startActivity(intent)
        }

        fun openWebsite(context: Context, url: String) {

            val httpUrl = if (!url.matches(Regex("https?://.*"))) {
                "http://$url"
            } else url

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(httpUrl)
            context.startActivity(intent)
        }

        fun sendEmail(context: Context, email: String) {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:" +  Uri.encode(email))
            context.startActivity(intent)
        }

        fun phoneCall(context: Context, number: String) {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$number")
            context.startActivity(intent)
        }


        fun openMap(context: Context, lat:String, lng:String, name:String) {
            val uri = Uri.parse("geo:0,0?q=$lat,$lng($name)")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.google.android.apps.maps")

            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }
        }
    }

}