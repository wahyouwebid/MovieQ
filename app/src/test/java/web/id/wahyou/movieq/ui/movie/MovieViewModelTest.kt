package web.id.wahyou.movieq.ui.movie

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import web.id.wahyou.movieq.data.FakeRemoteRepository

class MovieViewModelTest {

    lateinit var fakeRemoteRepository: FakeRemoteRepository

    @Before
    fun setUp() {
        fakeRemoteRepository = FakeRemoteRepository()
    }

    @Test
    fun testGetData() {
        val dataUpcoming = fakeRemoteRepository.getUpcomingMovie().blockingGet()
        val dataTopRated = fakeRemoteRepository.getTopRatedMovie().blockingGet()
        val dataPopular = fakeRemoteRepository.getPopularMovie().blockingGet()

        Assert.assertNotNull(dataUpcoming.data)
        Assert.assertEquals(20, dataUpcoming.data.size)

        Assert.assertNotNull(dataTopRated.data)
        Assert.assertEquals(20, dataTopRated.data.size)

        Assert.assertNotNull(dataPopular.data)
        Assert.assertEquals(20, dataPopular.data.size)
    }
}