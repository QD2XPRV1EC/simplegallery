package com.example.pawel.simplegallery.ui

import android.app.Application
import com.example.pawel.simplegallery.tools.ApplicationComponent
import com.example.pawel.simplegallery.tools.DaggerApplicationComponent
import com.example.pawel.simplegallery.tools.DaggerFragmentComponent
import com.example.pawel.simplegallery.tools.FragmentComponent

class SimpleGalleryApplication : Application() {

    lateinit var component: ApplicationComponent
    lateinit var fragmentComponent: FragmentComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder().build()
        fragmentComponent = DaggerFragmentComponent.builder().build()
    }

}