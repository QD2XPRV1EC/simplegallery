package com.example.pawel.simplegallery.ui.profile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.pawel.simplegallery.R
import com.example.pawel.simplegallery.databinding.ActivityProfileBinding
import com.example.pawel.simplegallery.tools.SimpleFragmentPagerAdapter
import com.example.pawel.simplegallery.ui.SimpleGalleryApplication
import javax.inject.Inject

class ProfileActivity : AppCompatActivity() {

    companion object {
        const val USER_ID_TYPE = "user_id_type"
    }

    @Inject
    lateinit var profileAlbumsFragment: ProfileAlbumsFragment

    @Inject
    lateinit var profileInfoFragment: ProfileInfoFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as SimpleGalleryApplication).component.inject(this)

        val binding = ActivityProfileBinding.inflate(layoutInflater, null, true)
        setContentView(binding.root)
        //binding.setLifecycleOwner(this)

        val bundle = Bundle()
        bundle.putInt(USER_ID_TYPE, intent.getIntExtra(USER_ID_TYPE, 0))

        profileInfoFragment.arguments = bundle
        profileAlbumsFragment.arguments = bundle

        val fragments = listOf(
                Pair(this.getString(R.string.profile), profileInfoFragment),
                Pair(this.getString(R.string.albums), profileAlbumsFragment))
        binding.viewPager.adapter = SimpleFragmentPagerAdapter(supportFragmentManager, fragments)
    }


}
