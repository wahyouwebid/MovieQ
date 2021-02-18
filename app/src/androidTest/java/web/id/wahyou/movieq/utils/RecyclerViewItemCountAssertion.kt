package web.id.wahyou.movieq.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.Assert

class RecyclerViewItemCountAssertion(
    private val expectedCount: Int = 0
) : ViewAssertion {

    override fun check(
        view: View,
        noViewFoundException: NoMatchingViewException?
    ) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }
        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        Assert.assertNotNull(adapter)
        MatcherAssert.assertThat(adapter!!.itemCount, Is.`is`(expectedCount))
    }
}