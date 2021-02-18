package web.id.wahyou.movieq.ui.movie.detail

import android.content.Context
import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import web.id.wahyou.movieq.R
import web.id.wahyou.movieq.model.DataMovie
import web.id.wahyou.movieq.utils.FakeDataDummy

class DetailMovieActivityTest {
    private val dataDummy: DataMovie = DataMovie(
        FakeDataDummy.movieData[0][0],
        FakeDataDummy.movieData[0][1],
        FakeDataDummy.movieData[0][2],
        FakeDataDummy.movieData[0][3],
        FakeDataDummy.movieData[0][4],
        FakeDataDummy.movieData[0][5],
        FakeDataDummy.movieData[0][6]
    )
    lateinit var context: Context

    @get:Rule
    val activityRule: ActivityTestRule<DetailMovieActivity> =
        object : ActivityTestRule<DetailMovieActivity>(DetailMovieActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext =
                    InstrumentationRegistry.getInstrumentation()
                        .targetContext
                val result = Intent(targetContext, DetailMovieActivity::class.java)
                result.putExtra(
                    "data", DataMovie(
                        FakeDataDummy.movieData[0][0],
                        FakeDataDummy.movieData[0][1],
                        FakeDataDummy.movieData[0][2],
                        FakeDataDummy.movieData[0][3],
                        FakeDataDummy.movieData[0][4],
                        FakeDataDummy.movieData[0][5],
                        FakeDataDummy.movieData[0][6]
                    )
                )
                return result
            }
        }

    @Before
    fun setUp() {
        context = activityRule.activity.applicationContext
    }

    @Test
    fun loadDetails() {
        Espresso.onView(withId(R.id.tvTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvTitle))
            .check(ViewAssertions.matches(withText(dataDummy.title)))

        Espresso.onView(withId(R.id.tvDescription))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvDescription))
            .check(ViewAssertions.matches(withText(dataDummy.description)))

        Espresso.onView(withId(R.id.tvPopularity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvPopularity))
            .check(ViewAssertions.matches(withText(dataDummy.popularity + " Viewers")))

        Espresso.onView(withId(R.id.tvRating))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvRating))
            .check(ViewAssertions.matches(withText(dataDummy.vote_average)))

        Espresso.onView(withId(R.id.tvRelease))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvRelease))
            .check(ViewAssertions.matches(withText(dataDummy.date)))

        Espresso.onView(withId(R.id.imgPoster))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.imgBackground))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}