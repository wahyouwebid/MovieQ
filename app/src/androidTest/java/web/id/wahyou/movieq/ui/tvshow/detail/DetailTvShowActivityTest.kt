package web.id.wahyou.movieq.ui.tvshow.detail

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import web.id.wahyou.movieq.R
import web.id.wahyou.movieq.data.network.ApiService
import web.id.wahyou.movieq.data.repository.Repository
import web.id.wahyou.movieq.ui.main.MainActivity
import web.id.wahyou.movieq.utils.EspressoIdlingResource
import web.id.wahyou.movieq.utils.RecyclerViewItemCountAssertion
import web.id.wahyou.movieq.utils.Utils
import javax.inject.Inject

@HiltAndroidTest
class DetailTvShowActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Suppress("DEPRECATION")
    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var endpoint: ApiService

    @Before
    fun setUp() {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.navigation_tvshow)))
            .perform(ViewActions.click())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        hiltRule.inject()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadDetails() {

        val getData = endpoint.getDetailTvShow(85271)
            .blockingGet()

        Espresso.onView(
            Matchers.allOf(ViewMatchers.isDisplayed(),
                ViewMatchers.withId(R.id.pgLoading)
            ))
        Espresso.onView(
            Matchers.allOf(ViewMatchers.isDisplayed(),
                ViewMatchers.withId(R.id.rvTvShow)
            ))
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.rvTvShow), ViewMatchers.isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.rvTvShow), ViewMatchers.isDisplayed()))
            .check(RecyclerViewItemCountAssertion(20))

        Espresso.onView(ViewMatchers.withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))

        Espresso.onView(ViewMatchers.withId(R.id.tvTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tvTitle))
            .check(ViewAssertions.matches(ViewMatchers.withText(getData.name)))

        Espresso.onView(ViewMatchers.withId(R.id.tvDescription))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tvDescription))
            .check(ViewAssertions.matches(ViewMatchers.withText(getData.overview)))

        Espresso.onView(ViewMatchers.withId(R.id.tvPopularity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tvPopularity))
            .check(ViewAssertions.matches(ViewMatchers.withText(getData.popularity.toString() + ". Viewers")))

        Espresso.onView(ViewMatchers.withId(R.id.tvRating))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tvRelease))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tvRelease))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.withText(
                        Utils.dateFormat(
                            getData.first_air_date!!,
                            "yyyy-mm-dd",
                            "dd MMMM yyyy"
                        )
                    )
                )
            )
    }
}