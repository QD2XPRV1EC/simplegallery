package com.example.pawel.simplegallery

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.pawel.simplegallery.ui.profile.ProfileActivity
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ProfileInstrumentedTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(ProfileActivity::class.java, true, false)


    @Before
    fun init() {
        val intent = Intent()
        intent.putExtra(ProfileActivity.USER_ID_TYPE, 1)
        activityRule.launchActivity(intent)
    }

    @Test
    fun testProfileInfo() {

        val notNullViews = listOf(
                R.id.nameValue,
                R.id.emailValue,
                R.id.phoneValue,
                R.id.websiteValue,
                R.id.addressValue,
                R.id.companyValue)

        for (notNullView in notNullViews) {
            onView(withId(notNullView))
                    .check(ViewAssertions.matches(Matchers.not(ViewMatchers.withText(""))))
        }
    }

}