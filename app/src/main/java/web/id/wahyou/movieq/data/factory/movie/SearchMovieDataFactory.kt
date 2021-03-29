package web.id.wahyou.movieq.data.factory.movie

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.data.source.movie.SearchMovieDataSource
import web.id.wahyou.movieq.state.MovieState
import javax.inject.Inject

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

class SearchMovieDataFactory @Inject constructor(
    private val movieSearchDataSource: SearchMovieDataSource
) : DataSource.Factory<Int, DataMovie>(){

    lateinit var keyword: String
    lateinit var liveData: MutableLiveData<MovieState>

    override fun create(): DataSource<Int, DataMovie> {
        return movieSearchDataSource.also {
            it.keyword = keyword
            it.liveData = liveData
        }
    }
}