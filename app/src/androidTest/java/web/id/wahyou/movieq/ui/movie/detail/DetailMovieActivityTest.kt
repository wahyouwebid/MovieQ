package web.id.wahyou.movieq.ui.movie.detail

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import web.id.wahyou.movieq.R
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.data.repository.Repository
import web.id.wahyou.movieq.state.MovieState
import web.id.wahyou.movieq.ui.main.MainActivity
import web.id.wahyou.movieq.ui.movie.MovieViewModel
import web.id.wahyou.movieq.utils.EspressoIdlingResource
import web.id.wahyou.movieq.utils.Utils.dateFormat
import javax.inject.Inject

@HiltAndroidTest
class DetailMovieActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Suppress("DEPRECATION")
    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Inject
    lateinit var repository: Repository

    lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        viewModel = MovieViewModel(repository)
        viewModel.getMovie()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadDetails() {
        Thread.sleep(3000)
        when(val state = viewModel.state.value){
            is MovieState.Result    -> {
                Assert.assertNotNull(state.data.data)
                val data : DataMovie = state.data.data[0]
                Espresso.onView(withId(R.id.rvMovie)).perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                        ViewActions.click()
                    ))

                    Thread.sleep(3000)
    
                    Espresso.onView(withId(R.id.tvTitle))
                        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                    Espresso.onView(withId(R.id.tvTitle))
                        .check(ViewAssertions.matches(withText(data.title)))
    
                    Espresso.onView(withId(R.id.tvDescription))
                        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                    Espresso.onView(withId(R.id.tvDescription))
                        .check(ViewAssertions.matches(withText(data.overview)))
    
                    Espresso.onView(withId(R.id.tvPopularity))
                        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                    Espresso.onView(withId(R.id.tvPopularity))
                        .check(ViewAssertions.matches(withText(data.popularity.toString() + ". Viewers")))
    
                    Espresso.onView(withId(R.id.tvRating))
                        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                    Espresso.onView(withId(R.id.tvRating))
                        .check(ViewAssertions.matches(withText(data.vote_average.toString())))
    
                    Espresso.onView(withId(R.id.tvRelease))
                        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                    Espresso.onView(withId(R.id.tvRelease))
                        .check(
                            ViewAssertions.matches(
                                withText(
                                    dateFormat(
                                        data.release_date!!,
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