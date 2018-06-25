package com.example.pawel.simplegallery

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.pawel.simplegallery.matchers.withRecyclerView
import com.example.pawel.simplegallery.ui.users.UsersActivity
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UsersInstrumentedTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(UsersActivity::class.java, true, true)

    @Test
    fun testList() {
        onView(withRecyclerView(R.id.recycler)
                .atPositionOnView(0, R.id.username))
                .check(matches(not(withText(""))))
    }
}
