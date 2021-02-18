package web.id.wahyou.movieq.ui.tvshow.detail

import android.content.Context
import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import web.id.wahyou.movieq.R
import web.id.wahyou.movieq.model.DataTvShow
import web.id.wahyou.movieq.utils.FakeDataDummy

class DetailTvShowActivityTest {
    private val dataDummy: DataTvShow = DataTvShow(
        FakeDataDummy.tvShowData[0][0],
        FakeDataDummy.tvShowData[0][1],
        FakeDataDummy.tvShowData[0][2],
        FakeDataDummy.tvShowData[0][3],
        FakeDataDummy.tvShowData[0][4],
        FakeDataDummy.tvShowData[0][5],
        FakeDataDummy.tvShowData[0][6]
    )
    lateinit var context: Context

    @get:Rule
    val activityRule: ActivityTestRule<DetailTvShowActivity> =
        object : ActivityTestRule<DetailTvShowActivity>(
            DetailTvShowActivity::class.java
        ) {
            override fun getActivityIntent(): Intent {
                val targetContext =
                    InstrumentationRegistry.getInstrumentation()
                        .targetContext
                val result = Intent(targetContext, DetailTvShowActivity::class.java)
                result.putExtra(
                    "data", DataTvShow(
                        FakeDataDummy.tvShowData[0][0],
                        FakeDataDummy.tvShowData[0][1],
                        FakeDataDummy.tvShowData[0][2],
                        FakeDataDummy.tvShowData[0][3],
                        FakeDataDummy.tvShowData[0][4],
                        FakeDataDummy.tvShowData[0][5],
                        FakeDataDummy.tvShowData[0][6]
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
        Espresso.onView(ViewMatchers.withId(R.id.tvTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tvTitle))
            .check(ViewAssertions.matches(ViewMatchers.withText(dataDummy.title)))

        Espresso.onView(ViewMatchers.withId(R.id.tvDescription))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tvDescription))
            .check(ViewAssertions.matches(ViewMatchers.withText(dataDummy.description)))

        Espresso.onView(ViewMatchers.withId(R.id.tvPopularity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tvPopularity))
            .check(ViewAssertions.matches(ViewMatchers.withText(dataDummy.popularity + " Viewers")))

        Espresso.onView(ViewMatchers.withId(R.id.tvRating))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tvRating))
            .check(ViewAssertions.matches(ViewMatchers.withText(dataDummy.vote_average)))

        Espresso.onView(ViewMatchers.withId(R.id.tvRelease))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tvRelease))
            .check(ViewAssertions.matches(ViewMatchers.withText(dataDummy.date)))

        Espresso.onView(ViewMatchers.withId(R.id.imgPoster))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.imgBackground))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}