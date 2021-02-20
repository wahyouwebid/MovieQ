package web.id.wahyou.movieq.data.factory.movie

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.data.source.movie.UpcomingMovieDataSource
import web.id.wahyou.movieq.state.MovieState
import javax.inject.Inject

class UpcomingMovieDataFactory @Inject constructor(
    private val upcomingMovieDataSource: UpcomingMovieDataSource
) : DataSource.Factory<Int, DataMovie>(){

    lateinit var liveData: MutableLiveData<MovieState>

    override fun create(): DataSource<Int, DataMovie> {
        return upcomingMovieDataSource.also {
            it.liveData = liveData
        }
    }
}