package web.id.wahyou.movieq.ui.tvshow

import org.junit.Assert
import org.junit.Test
import org.junit.Before
import web.id.wahyou.movieq.model.DataTvShow

class TvShowViewModelTest {

    lateinit var viewModel: TvShowViewModel

    @Before
    fun setup() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun testGetData() {
        val data: List<DataTvShow> = viewModel.getData()
        Assert.assertNotNull(data)
        Assert.assertEquals(10, data.size)
    }
}