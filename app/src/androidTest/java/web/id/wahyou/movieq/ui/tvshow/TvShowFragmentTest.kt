package web.id.wahyou.movieq.ui.tvshow

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import web.id.wahyou.movieq.R
import web.id.wahyou.movieq.ui.main.MainActivity
import web.id.wahyou.movieq.utils.EspressoIdlingResource
import web.id.wahyou.movieq.utils.RecyclerViewItemCountAssertion

@HiltAndroidTest
class TvShowFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Suppress("DEPRECATION")
    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)

        Espresso.onView(Matchers.allOf(withId(R.id.navigation_tvshow)))
            .perform(ViewActions.click())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadTvShows() {
        Espresso.onView(withId(R.id.rvTvShow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.rvTvShow))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
        Espresso.onView(withId(R.id.rvTvShow))
            .check(RecyclerViewItemCountAssertion(20))
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }
}