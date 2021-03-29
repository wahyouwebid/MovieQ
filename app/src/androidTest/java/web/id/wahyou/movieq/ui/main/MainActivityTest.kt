package web.id.wahyou.movieq.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import web.id.wahyou.movieq.R
import web.id.wahyou.movieq.utils.EspressoIdlingResource

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

@HiltAndroidTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Suppress("DEPRECATION")
    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun checkBottomBarDisplayed() {
        onView(withId(R.id.navView))
                .perform(click())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkOtherBottomBar() {
        onView(withId(R.id.navigation_tvshow))
                .perform(click())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.navigation_movie))
                .perform(click())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.navigation_tvshow))
                .perform(click())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.navigation_movie))
                .perform(click())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.navigation_movie))
                .perform(click())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }
}