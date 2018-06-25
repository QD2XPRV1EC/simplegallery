package com.example.pawel.simplegallery.ui.photo

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.pawel.simplegallery.databinding.ActivityPhotoBinding
import com.example.pawel.simplegallery.tools.ViewModelFactory
import com.example.pawel.simplegallery.ui.SimpleGalleryApplication
import javax.inject.Inject

class PhotoActivity : AppCompatActivity() {

    companion object {
        const val PHOTO_ID_TYPE = "photo_id_type"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as SimpleGalleryApplication).component.inject(this)

        val binding = ActivityPhotoBinding.inflate(layoutInflater, null, true)
        setContentView(binding.root)
        binding.setLifecycleOwner(this)

        val photoId = intent.getIntExtra(PHOTO_ID_TYPE, 0)
        val model = ViewModelProviders.of(this, viewModelFactory.withId(photoId))[PhotoViewModel::class.java]
        binding.model = model
    }
}
