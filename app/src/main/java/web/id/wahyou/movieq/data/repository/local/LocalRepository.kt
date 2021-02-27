package web.id.wahyou.movieq.data.repository.local

import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import web.id.wahyou.movieq.data.database.RoomDb
import web.id.wahyou.movieq.data.database.model.MovieEntity
import web.id.wahyou.movieq.data.database.model.TvShowEntity
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.data.model.tvshow.DataTvShow
import web.id.wahyou.movieq.data.repository.Repository
import web.id.wahyou.movieq.state.DetailMovieState
import web.id.wahyou.movieq.state.DetailTvShowState
import web.id.wahyou.movieq.state.MovieState
import web.id.wahyou.movieq.state.TvShowState
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val database: RoomDb,
    private val config: PagedList.Config
) : Repository {

    var disposable: CompositeDisposable = CompositeDisposable()

    override fun getNowPlayingMovie(callback: MutableLiveData<MovieState>) {
        throw UnsupportedOperationException()
    }

    override fun getUpcomingMovie(callback: MutableLiveData<MovieState>) {
        throw UnsupportedOperationException()
    }

    override fun getPopularMovie(callback: MutableLiveData<MovieState>) {
        throw UnsupportedOperationException()
    }

    override fun getTopRatedMovie(callback: MutableLiveData<MovieState>) {
        throw UnsupportedOperationException()
    }

    override fun getDetailMovie(movieId: Int, callback: MutableLiveData<DetailMovieState>) {
        throw UnsupportedOperationException()
    }

    override fun getAllUpcomingMovie(
        callback: MutableLiveData<MovieState>,
        data: MutableLiveData<PagedList<DataMovie>>
    ) {
        throw UnsupportedOperationException()
    }

    override fun getAllTopRatedMovie(
        callback: MutableLiveData<MovieState>,
        data: MutableLiveData<PagedList<DataMovie>>
    ) {
        throw UnsupportedOperationException()
    }

    override fun getAllPopularMovie(
        callback: MutableLiveData<MovieState>,
        data: MutableLiveData<PagedList<DataMovie>>
    ) {
        throw UnsupportedOperationException()
    }

    override fun searchMovie(
        query: String,
        callback: MutableLiveData<MovieState>,
        data: MutableLiveData<PagedList<DataMovie>>
    ) {
        throw UnsupportedOperationException()
    }

    override fun getAiringTodayTvShow(callback: MutableLiveData<TvShowState>) {
        throw UnsupportedOperationException()
    }

    override fun getTopRatedTvShow(callback: MutableLiveData<TvShowState>) {
        throw UnsupportedOperationException()
    }

    override fun getPopularTvShow(callback: MutableLiveData<TvShowState>) {
        throw UnsupportedOperationException()
    }

    override fun getDetailTvShow(tvId: Int, callback: MutableLiveData<DetailTvShowState>) {
        throw UnsupportedOperationException()
    }

    override fun getAllAiringTodayTvShow(
        callback: MutableLiveData<TvShowState>,
        data: MutableLiveData<PagedList<DataTvShow>>
    ) {
        throw UnsupportedOperationException()
    }

    override fun getAllTopRatedTvShow(
        callback: MutableLiveData<TvShowState>,
        data: MutableLiveData<PagedList<DataTvShow>>
    ) {
        throw UnsupportedOperationException()
    }

    override fun getAllPopularTvShow(
        callback: MutableLiveData<TvShowState>,
        data: MutableLiveData<PagedList<DataTvShow>>
    ) {
        throw UnsupportedOperationException()
    }

    override fun searchTvShow(
        query: String,
        callback: MutableLiveData<TvShowState>,
        data: MutableLiveData<PagedList<DataTvShow>>
    ) {
        throw UnsupportedOperationException()
    }

    override fun getFavoriteMovie(data: MutableLiveData<PagedList<MovieEntity>>) {
        LivePagedListBuilder(
            database.movie().getData(),
            config
        ).build().observeForever(data::postValue)
    }

    override fun searchFavoriteMovie(query: String, data: MutableLiveData<PagedList<MovieEntity>>) {
        LivePagedListBuilder(
            database.movie().searchData("%$query%"),
            config
        ).build().observeForever(data::postValue)
    }

    override fun getFavoriteTvShow(data: MutableLiveData<PagedList<TvShowEntity>>) {
        LivePagedListBuilder(
            database.tvshow().getData(),
            config
        ).build().observeForever(data::postValue)
    }

    override fun searchFavoriteTvShow(query: String, data: MutableLiveData<PagedList<TvShowEntity>>) {
        LivePagedListBuilder(
            database.tvshow().searchData("%$query%"),
            config
        ).build().observeForever(data::postValue)
    }

    override fun addDataMovie(data: MovieEntity) {
        database.movie().add(data)
    }

    override fun checkDataMovie(data: MovieEntity): List<MovieEntity> {
        return database.movie().getDataById(data.id)
    }

    override fun deleteDataMovie(data: MovieEntity) {
        database.movie().delete(data)
    }

    override fun addDataTvShow(data: TvShowEntity) {
        database.tvshow().add(data)
    }

    override fun checkDataTvShow(data: TvShowEntity): List<TvShowEntity> {
        return database.tvshow().getDataById(data.id)
    }

    override fun deleteDataTvShow(data: TvShowEntity) {
        database.tvshow().delete(data)
    }

    override fun getDisposible(): CompositeDisposable = disposable

    override fun getDatabase(): RoomDb = database
}