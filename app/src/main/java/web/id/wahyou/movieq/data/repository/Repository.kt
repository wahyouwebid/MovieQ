package web.id.wahyou.movieq.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import web.id.wahyou.movieq.data.database.RoomDb
import web.id.wahyou.movieq.data.database.model.MovieEntity
import web.id.wahyou.movieq.data.database.model.TvShowEntity
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.data.model.tvshow.DataTvShow
import web.id.wahyou.movieq.state.*

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

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
    fun getAiringTodayTvShow(callback: MutableLiveData<TvShowState>)
    fun getTopRatedTvShow(callback: MutableLiveData<TvShowState>)
    fun getPopularTvShow(callback: MutableLiveData<TvShowState>)
    fun getDetailTvShow(tvId: Int, callback : MutableLiveData<DetailTvShowState>)

    //See All TV Show
    fun getAllAiringTodayTvShow(
            callback : MutableLiveData<TvShowState>,
            data : MutableLiveData<PagedList<DataTvShow>>
    )
    fun getAllTopRatedTvShow(
            callback : MutableLiveData<TvShowState>,
            data : MutableLiveData<PagedList<DataTvShow>>
    )
    fun getAllPopularTvShow(
            callback : MutableLiveData<TvShowState>,
            data : MutableLiveData<PagedList<DataTvShow>>
    )
    fun searchTvShow(
            query: String,
            callback: MutableLiveData<TvShowState>,
            data : MutableLiveData<PagedList<DataTvShow>>
    )

    //Favorite
    fun getFavoriteMovie(data : MutableLiveData<PagedList<MovieEntity>>)
    fun searchFavoriteMovie(
        query: String,
        data : MutableLiveData<PagedList<MovieEntity>>
    )
    fun getFavoriteTvShow(data : MutableLiveData<PagedList<TvShowEntity>>)
    fun searchFavoriteTvShow(
        query: String,
        data : MutableLiveData<PagedList<TvShowEntity>>
    )
    fun addDataMovie(data : MovieEntity)
    fun checkDataMovie(data: MovieEntity) : List<MovieEntity>
    fun deleteDataMovie(data : MovieEntity)
    fun addDataTvShow(data : TvShowEntity)
    fun checkDataTvShow(data : TvShowEntity) : List<TvShowEntity>
    fun deleteDataTvShow(data : TvShowEntity)

    //Videos
    fun getVideos(
            type: String,
            id: Int,
            callback : MutableLiveData<VideoState>
    )

    fun getDisposible() : CompositeDisposable
    fun getDatabase() : RoomDb
}