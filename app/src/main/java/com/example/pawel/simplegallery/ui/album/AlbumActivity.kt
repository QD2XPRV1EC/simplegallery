package com.example.pawel.simplegallery.ui.album

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.pawel.simplegallery.databinding.ActivityAlbumBinding
import com.example.pawel.simplegallery.tools.ViewModelFactory
import com.example.pawel.simplegallery.ui.SimpleGalleryApplication
import javax.inject.Inject

class AlbumActivity : AppCompatActivity() {

    companion object {
        const val ALBUM_ID_TYPE = "album_id_type"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as SimpleGalleryApplication).component.inject(this)

        val binding = ActivityAlbumBinding.inflate(layoutInflater, null, true)
        setContentView(binding.root)
        binding.setLifecycleOwner(this)

        val albumId = intent.getIntExtra(ALBUM_ID_TYPE, 0)
        val model = ViewModelProviders.of(this, viewModelFactory.withId(albumId))[AlbumViewModel::class.java]
        binding.model = model
    }



}
