package web.id.wahyou.movieq.ui.main

import android.content.Intent
import androidx.annotation.UiThread
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsNull
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import web.id.wahyou.movieq.R

class MainActivityTest{
    @get:Rule
    val activityRule: ActivityTestRule<MainActivity> =
            ActivityTestRule(MainActivity::class.java)
    lateinit var mainActivity: MainActivity

    @Before
    fun setUp() {
        mainActivity = activityRule.activity
        activityRule.launchActivity(Intent())
        Assert.assertThat(activityRule, IsNull.notNullValue())
    }

    @Test
    fun checkBottomBarDisplayed() {
        Espresso.onView(withId(R.id.navView))
                .perform(ViewActions.click())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    @UiThread
    fun checkOtherBottomBar() {
        Espresso.onView(allOf(withId(R.id.navigation_tvshow)))
                .perform(ViewActions.click())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}