package com.example.pawel.simplegallery

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.pawel.simplegallery.ui.photo.PhotoActivity
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PhotoInstrumentedTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(PhotoActivity::class.java, true, false)


    @Before
    fun init() {
        val intent = Intent()
        intent.putExtra(PhotoActivity.PHOTO_ID_TYPE, 1)
        activityRule.launchActivity(intent)
    }

    @Test
    fun testPhoto() {
        onView(ViewMatchers.withId(R.id.title))
                .check(ViewAssertions.matches(Matchers.not(ViewMatchers.withText(""))))
    }


}