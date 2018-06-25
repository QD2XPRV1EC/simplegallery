package com.example.pawel.simplegallery.ui.users

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.pawel.simplegallery.databinding.ActivityUsersBinding
import com.example.pawel.simplegallery.tools.ViewModelFactory
import com.example.pawel.simplegallery.ui.SimpleGalleryApplication
import javax.inject.Inject

class UsersActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as SimpleGalleryApplication).component.inject(this)

        val binding = ActivityUsersBinding.inflate(layoutInflater, null, true)
        setContentView(binding.root)
        binding.setLifecycleOwner(this)

        val model = ViewModelProviders.of(this, viewModelFactory.raw())[UsersViewModel::class.java]
        binding.model = model
    }
}
