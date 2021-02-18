package web.id.wahyou.movieq.ui.movie

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import web.id.wahyou.movieq.model.DataMovie

class MovieViewModelTest {

    lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun testGetData() {
        val data: List<DataMovie> = viewModel.getData()
        Assert.assertNotNull(data)
        Assert.assertEquals(10, data.size)
    }
}