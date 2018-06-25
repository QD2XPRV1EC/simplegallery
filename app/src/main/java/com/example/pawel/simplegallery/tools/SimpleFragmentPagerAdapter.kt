package com.example.pawel.simplegallery.tools

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class SimpleFragmentPagerAdapter(
        fragmentManager: FragmentManager,
        private val fragments: List<Pair<String, Fragment>>
) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int) = fragments[position].second
    override fun getCount() = fragments.size
    override fun getPageTitle(position: Int) = fragments[position].first
}