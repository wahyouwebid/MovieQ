package web.id.wahyou.movieq.data.factory.tvshow

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import web.id.wahyou.movieq.data.model.tvshow.DataTvShow
import web.id.wahyou.movieq.data.source.tv.SearchTvDataSource
import web.id.wahyou.movieq.state.TvShowState
import javax.inject.Inject

class SearchTvDataFactory @Inject constructor(
    private val tvSearchDataSource: SearchTvDataSource
) : DataSource.Factory<Int, DataTvShow>(){

    lateinit var keyword: String
    lateinit var liveData: MutableLiveData<TvShowState>

    override fun create(): DataSource<Int, DataTvShow> {
        return tvSearchDataSource.also {
            it.keyword = keyword
            it.liveData = liveData
        }
    }
}