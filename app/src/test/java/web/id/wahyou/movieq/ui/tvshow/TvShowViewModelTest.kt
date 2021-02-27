package web.id.wahyou.movieq.ui.tvshow

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import web.id.wahyou.movieq.data.FakeRemoteRepository

class TvShowViewModelTest {

    lateinit var fakeRemoteRepository: FakeRemoteRepository

    @Before
    fun setup() {
        fakeRemoteRepository = FakeRemoteRepository()
    }

    @Test
    fun testGetData() {
        val getData = fakeRemoteRepository.getTv()
            .blockingGet()

        Assert.assertNotNull(getData.data)
        Assert.assertEquals(20, getData.data.size)
    }
}