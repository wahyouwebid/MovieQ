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
        val getData = fakeRemoteRepository.getMovie()
            .blockingGet()

        Assert.assertNotNull(getData.data)
        Assert.assertEquals(20, getData.data.size)
    }
}