package com.example.pawel.simplegallery.ui.profile


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pawel.simplegallery.databinding.FragmentProfileAlbumsBinding
import com.example.pawel.simplegallery.tools.ViewModelFactory
import com.example.pawel.simplegallery.ui.SimpleGalleryApplication
import com.example.pawel.simplegallery.ui.profile.ProfileActivity.Companion.USER_ID_TYPE
import javax.inject.Inject

class ProfileAlbumsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    var model:ProfileViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as SimpleGalleryApplication).fragmentComponent.inject(this)

        val userId = arguments?.getInt(USER_ID_TYPE) ?: 0
        activity?.let {
            model = ViewModelProviders.of(it, viewModelFactory.withId(userId)).get(ProfileViewModel::class.java)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {



        val binding = FragmentProfileAlbumsBinding.inflate(layoutInflater, null, true)
        binding.model = model
        binding.setLifecycleOwner(this)
        return binding.root
    }


}
