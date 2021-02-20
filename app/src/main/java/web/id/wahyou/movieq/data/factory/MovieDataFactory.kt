package web.id.wahyou.movieq.data.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.data.source.movie.MovieDataSource
import web.id.wahyou.movieq.state.MovieState
import javax.inject.Inject

class MovieDataFactory @Inject constructor(
    private val movieDataSource: MovieDataSource
) : DataSource.Factory<Int, DataMovie>(){

    lateinit var liveData: MutableLiveData<MovieState>

    override fun create(): DataSource<Int, DataMovie> {
        return movieDataSource.also {
            it.liveData = liveData
        }
    }
}