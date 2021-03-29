package web.id.wahyou.movieq.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    private const val TESTING = "TESTING"
    val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)
    val espressoTest = CountingIdlingResource(TESTING)

    var isTv = false
    var isMovie = false

    var isTestingMode = false

    fun increment() {
        espressoTestIdlingResource.increment()
    }

    fun incrementTest(){
        espressoTest.increment()
    }

    fun decrement() {
        espressoTestIdlingResource.decrement()
    }

    fun decrementTest(){
        espressoTest.decrement()
    }

    fun getEspressoIdlingResource(): IdlingResource {
        return espressoTestIdlingResource
    }
}