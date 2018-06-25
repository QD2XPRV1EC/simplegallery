package com.example.pawel.simplegallery

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.pawel.simplegallery.matchers.withRecyclerView
import com.example.pawel.simplegallery.ui.album.AlbumActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumInstrumentedTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(AlbumActivity::class.java, true, false)


    @Before
    fun init() {
        val intent = Intent()
        intent.putExtra(AlbumActivity.ALBUM_ID_TYPE, 1)
        activityRule.launchActivity(intent)
    }

    @Test
    fun testList() {
        onView(withRecyclerView(R.id.recycler)
                .atPositionOnView(0, R.id.photo))
                .check(matches(isDisplayed()))
    }




}