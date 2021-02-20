package web.id.wahyou.movieq.ui.movie

import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ScrollView
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ScrollToAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import web.id.wahyou.movieq.R
import web.id.wahyou.movieq.ui.main.MainActivity
import web.id.wahyou.movieq.utils.EspressoIdlingResource
import web.id.wahyou.movieq.utils.RecyclerViewItemCountAssertion


@HiltAndroidTest
class MovieFragmentTest {
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
    fun loadMovies() {
        Espresso.onView(withId(R.id.rvUpcomingMovie))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.rvTopRatedMovie))
                .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.rvUpcomingMovie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
        Espresso.onView(withId(R.id.rvUpcomingMovie))
            .check(RecyclerViewItemCountAssertion(20))

        Espresso.onView(withId(R.id.rvTopRatedMovie))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
        Espresso.onView(withId(R.id.rvTopRatedMovie))
                .check(RecyclerViewItemCountAssertion(20))
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }
}