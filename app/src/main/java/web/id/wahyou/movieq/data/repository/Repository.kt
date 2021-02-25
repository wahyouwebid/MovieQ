package web.id.wahyou.movieq.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import retrofit2.http.GET
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.data.model.movie.ResponseMovie
import web.id.wahyou.movieq.data.model.tvshow.DataTvShow
import web.id.wahyou.movieq.state.DetailMovieState
import web.id.wahyou.movieq.state.DetailTvShowState
import web.id.wahyou.movieq.state.MovieState
import web.id.wahyou.movieq.state.TvShowState

interface Repository {
    //Movies
    fun getNowPlayingMovie(callback : MutableLiveData<MovieState>)
    fun getUpcomingMovie(callback : MutableLiveData<MovieState>)
    fun getPopularMovie(callback : MutableLiveData<MovieState>)
    fun getTopRatedMovie(callback : MutableLiveData<MovieState>)
    fun getDetailMovie(movieId: Int, callback : MutableLiveData<DetailMovieState>)

    //See All Movie
    fun getAllUpcomingMovie(
        callback : MutableLiveData<MovieState>,
        data : MutableLiveData<PagedList<DataMovie>>
    )

    fun getAllTopRatedMovie(
        callback : MutableLiveData<MovieState>,
        data : MutableLiveData<PagedList<DataMovie>>
    )

    fun getAllPopularMovie(
        callback : MutableLiveData<MovieState>,
        data : MutableLiveData<PagedList<DataMovie>>
    )

    fun searchMovie(
            query: String,
            callback : MutableLiveData<MovieState>,
            data : MutableLiveData<PagedList<DataMovie>>
    )

    //TV Show
    fun getTvShow(callback: MutableLiveData<TvShowState>)
    fun getDetailTvShow(tvId: Int, callback : MutableLiveData<DetailTvShowState>)

    fun searchTvShow(
            query: String,
            callback: MutableLiveData<TvShowState>,
            data : MutableLiveData<PagedList<DataTvShow>>
    )

    fun getDisposible() : CompositeDisposable
}