package web.id.wahyou.movieq.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.data.model.tvshow.DataTvShow
import web.id.wahyou.movieq.data.repository.remote.RemoteRepository
import web.id.wahyou.movieq.state.DetailMovieState
import web.id.wahyou.movieq.state.DetailTvShowState
import web.id.wahyou.movieq.state.MovieState
import web.id.wahyou.movieq.state.TvShowState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(
    private val remoteRepository: RemoteRepository
) : Repository {
    override fun getNowPlayingMovie(
            callback: MutableLiveData<MovieState>
    ) = remoteRepository.getNowPlayingMovie(callback)

    override fun getUpcomingMovie(
            callback: MutableLiveData<MovieState>
    ) = remoteRepository.getUpcomingMovie(callback)

    override fun getPopularMovie(
            callback: MutableLiveData<MovieState>
    ) = remoteRepository.getPopularMovie(callback)

    override fun getTopRatedMovie(
            callback: MutableLiveData<MovieState>
    ) = remoteRepository.getTopRatedMovie(callback)

    override fun getDetailMovie(
            movieId: Int, callback: MutableLiveData<DetailMovieState>
    ) = remoteRepository.getDetailMovie(movieId, callback)

    override fun getAllUpcomingMovie(
        callback: MutableLiveData<MovieState>,
        data: MutableLiveData<PagedList<DataMovie>>
    ) = remoteRepository.getAllUpcomingMovie(callback, data)

    override fun getAllTopRatedMovie(
        callback: MutableLiveData<MovieState>,
        data: MutableLiveData<PagedList<DataMovie>>
    ) = remoteRepository.getAllTopRatedMovie(callback, data)

    override fun getAllPopularMovie(
        callback: MutableLiveData<MovieState>,
        data: MutableLiveData<PagedList<DataMovie>>
    ) = remoteRepository.getAllPopularMovie(callback, data)

    override fun searchMovie(
            query: String,
            callback: MutableLiveData<MovieState>,
            data: MutableLiveData<PagedList<DataMovie>>
    ) = remoteRepository.searchMovie(query, callback, data)

    override fun getTvShow(
            callback: MutableLiveData<TvShowState>
    ) = remoteRepository.getTvShow(callback)

    override fun getDetailTvShow(
            tvId: Int, callback: MutableLiveData<DetailTvShowState>
    ) = remoteRepository.getDetailTvShow(tvId, callback)

    override fun searchTvShow(
            query: String,
            callback: MutableLiveData<TvShowState>,
            data: MutableLiveData<PagedList<DataTvShow>>
    ) = remoteRepository.searchTvShow(query, callback, data)

    override fun getDisposible(): CompositeDisposable =
        remoteRepository.getDisposible()
}