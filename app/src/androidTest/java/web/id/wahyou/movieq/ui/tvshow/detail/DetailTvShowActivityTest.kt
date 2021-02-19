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
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import web.id.wahyou.movieq.R
import web.id.wahyou.movieq.data.model.tvshow.DataTvShow
import web.id.wahyou.movieq.data.repository.Repository
import web.id.wahyou.movieq.state.TvShowState
import web.id.wahyou.movieq.ui.main.MainActivity
import web.id.wahyou.movieq.ui.tvshow.TvShowViewModel
import web.id.wahyou.movieq.utils.EspressoIdlingResource
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

    lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        Espresso.onView(ViewMatchers.withId(R.id.navigation_tvshow))
            .perform(ViewActions.click())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        hiltRule.inject()
        viewModel = TvShowViewModel(repository)
        viewModel.getTvShow()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadDetails() {
        Thread.sleep(3000)
        when(val state = viewModel.state.value){
            is TvShowState.Result    -> {
                Assert.assertNotNull(state.data.data)
                val data : DataTvShow = state.data.data[0]
                Espresso.onView(ViewMatchers.withId(R.id.rvTvShow)).perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                        ViewActions.click()
                    ))

                Thread.sleep(3000)

                Espresso.onView(ViewMatchers.withId(R.id.tvTitle))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                Espresso.onView(ViewMatchers.withId(R.id.tvTitle))
                    .check(ViewAssertions.matches(ViewMatchers.withText(data.name)))

                Espresso.onView(ViewMatchers.withId(R.id.tvDescription))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                Espresso.onView(ViewMatchers.withId(R.id.tvDescription))
                    .check(ViewAssertions.matches(ViewMatchers.withText(data.overview)))

                Espresso.onView(ViewMatchers.withId(R.id.tvPopularity))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                Espresso.onView(ViewMatchers.withId(R.id.tvPopularity))
                    .check(ViewAssertions.matches(ViewMatchers.withText(data.popularity.toString() + ". Viewers")))

                Espresso.onView(ViewMatchers.withId(R.id.tvRating))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                Espresso.onView(ViewMatchers.withId(R.id.tvRating))
                    .check(ViewAssertions.matches(ViewMatchers.withText(data.vote_average.toString())))

                Espresso.onView(ViewMatchers.withId(R.id.tvRelease))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                Espresso.onView(ViewMatchers.withId(R.id.tvRelease))
                    .check(
                        ViewAssertions.matches(
                            ViewMatchers.withText(
                                Utils.dateFormat(
                                    data.first_air_date!!,
                                    "yyyy-mm-dd",
                                    "dd MMMM yyyy"
                                )
                            )
                        )
                    )
            }
            else -> {
                throw UnknownError()
            }
        }
    }
}