package web.id.wahyou.movieq.data.factory.tvshow

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import web.id.wahyou.movieq.data.model.tvshow.DataTvShow
import web.id.wahyou.movieq.data.source.tv.AiringTvDataSource
import web.id.wahyou.movieq.state.TvShowState
import javax.inject.Inject

class AiringTvShowDataFactory @Inject constructor(
    private val airingTvDataSource: AiringTvDataSource
) : DataSource.Factory<Int, DataTvShow>(){

    lateinit var liveData: MutableLiveData<TvShowState>

    override fun create(): DataSource<Int, DataTvShow> {
        return airingTvDataSource.also {
            it.liveData = liveData
        }
    }
}