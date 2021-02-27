package web.id.wahyou.movieq.data.repository.remote

import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import web.id.wahyou.movieq.data.database.RoomDb
import web.id.wahyou.movieq.data.database.model.MovieEntity
import web.id.wahyou.movieq.data.database.model.TvShowEntity
import web.id.wahyou.movieq.data.factory.Factory
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.data.model.tvshow.DataTvShow
import web.id.wahyou.movieq.data.network.ApiService
import web.id.wahyou.movieq.data.repository.Repository
import web.id.wahyou.movieq.state.DetailMovieState
import web.id.wahyou.movieq.state.DetailTvShowState
import web.id.wahyou.movieq.state.MovieState
import web.id.wahyou.movieq.state.TvShowState
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val apiService: ApiService,
    private val config : PagedList.Config,
    private val factory: Factory
) : Repository {

    var disposable: CompositeDisposable = CompositeDisposable()

    override fun getDisposible(): CompositeDisposable = disposable

    override fun getNowPlayingMovie(callback: MutableLiveData<MovieState>) {
        apiService.getNowPlayingMovie()
                .map<MovieState>(MovieState::Result)
                .onErrorReturn(MovieState::Error)
                .toFlowable()
                .startWith(MovieState.Loading)
                .subscribe(callback::postValue)
                .let { return@let disposable::add }
    }

    override fun getUpcomingMovie(callback: MutableLiveData<MovieState>) {
        apiService.getUpcomingMovie()
                .map<MovieState>(MovieState::Result)
                .onErrorReturn(MovieState::Error)
                .toFlowable()
                .startWith(MovieState.Loading)
                .subscribe(callback::postValue)
                .let { return@let disposable::add }
    }

    override fun getPopularMovie(callback: MutableLiveData<MovieState>) {
        apiService.getPopularMovie()
                .map<MovieState>(MovieState::Result)
                .onErrorReturn(MovieState::Error)
                .toFlowable()
                .startWith(MovieState.Loading)
                .subscribe(callback::postValue)
                .let { return@let disposable::add }
    }

    override fun getTopRatedMovie(callback: MutableLiveData<MovieState>) {
        apiService.getTopRatedMovie()
                .map<MovieState>(MovieState::Result)
                .onErrorReturn(MovieState::Error)
                .toFlowable()
                .startWith(MovieState.Loading)
                .subscribe(callback::postValue)
                .let { return@let disposable::add }
    }

    override fun getDetailMovie(movieId: Int, callback: MutableLiveData<DetailMovieState>) {
        apiService.getDetailMovie(movieId)
                .map<DetailMovieState>(DetailMovieState::Result)
                .onErrorReturn(DetailMovieState::Error)
                .toFlowable()
                .startWith(DetailMovieState.Loading)
                .subscribe(callback::postValue)
                .let { return@let disposable::add }
    }

    override fun getAllUpcomingMovie(
        callback: MutableLiveData<MovieState>,
        data: MutableLiveData<PagedList<DataMovie>>
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            LivePagedListBuilder(
                factory.upcomingMovieDataFactory.also {
                    it.liveData = callback
                },
                config
            ).build().observeForever(data::postValue)
        }
    }

    override fun getAllTopRatedMovie(
        callback: MutableLiveData<MovieState>,
        data: MutableLiveData<PagedList<DataMovie>>
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            LivePagedListBuilder(
                factory.topRatedMovieDataFactory.also {
                    it.liveData = callback
                },
                config
            ).build().observeForever(data::postValue)
        }
    }

    override fun getAllPopularMovie(
        callback: MutableLiveData<MovieState>,
        data: MutableLiveData<PagedList<DataMovie>>
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            LivePagedListBuilder(
                factory.popularMovieDataFactory.also {
                    it.liveData = callback
                },
                config
            ).build().observeForever(data::postValue)
        }
    }

    override fun searchMovie(query: String, callback: MutableLiveData<MovieState>, data: MutableLiveData<PagedList<DataMovie>>) {
        CoroutineScope(Dispatchers.Main).launch {
            LivePagedListBuilder(
                    factory.searchMovieDataFactory.also {
                        it.liveData = callback
                        it.keyword = query
                    },
                    config
            ).build().observeForever(data::postValue)
        }
    }

    override fun getAiringTodayTvShow(callback: MutableLiveData<TvShowState>) {
        apiService.getAiringTodayTvShow()
                .map<TvShowState>(TvShowState::Result)
                .onErrorReturn(TvShowState::Error)
                .toFlowable()
                .startWith(TvShowState.Loading)
                .subscribe(callback::postValue)
                .let { return@let disposable::add}
    }

    override fun getTopRatedTvShow(callback: MutableLiveData<TvShowState>) {
        apiService.getTopRatedTvShow()
                .map<TvShowState>(TvShowState::Result)
                .onErrorReturn(TvShowState::Error)
                .toFlowable()
                .startWith(TvShowState.Loading)
                .subscribe(callback::postValue)
                .let { return@let disposable::add}
    }

    override fun getPopularTvShow(callback: MutableLiveData<TvShowState>) {
        apiService.getPopularTvShow()
                .map<TvShowState>(TvShowState::Result)
                .onErrorReturn(TvShowState::Error)
                .toFlowable()
                .startWith(TvShowState.Loading)
                .subscribe(callback::postValue)
                .let { return@let disposable::add}
    }

    override fun getDetailTvShow(tvId: Int, callback: MutableLiveData<DetailTvShowState>) {
        apiService.getDetailTvShow(tvId)
                .map<DetailTvShowState>(DetailTvShowState::Result)
                .onErrorReturn(DetailTvShowState::Error)
                .toFlowable()
                .startWith(DetailTvShowState.Loading)
                .subscribe(callback::postValue)
                .let { return@let disposable::add}
    }

    override fun getAllAiringTodayTvShow(
            callback: MutableLiveData<TvShowState>,
            data: MutableLiveData<PagedList<DataTvShow>>
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            LivePagedListBuilder(
                    factory.airingTvShowDataFactory.also {
                        it.liveData = callback
                    },
                    config
            ).build().observeForever(data::postValue)
        }
    }

    override fun getAllTopRatedTvShow(
            callback: MutableLiveData<TvShowState>,
            data: MutableLiveData<PagedList<DataTvShow>>
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            LivePagedListBuilder(
                    factory.topRatedTvShowDataFactory.also {
                        it.liveData = callback
                    },
                    config
            ).build().observeForever(data::postValue)
        }
    }

    override fun getAllPopularTvShow(
            callback: MutableLiveData<TvShowState>,
            data: MutableLiveData<PagedList<DataTvShow>>
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            LivePagedListBuilder(
                    factory.popularTvShowDataFactory.also {
                        it.liveData = callback
                    },
                    config
            ).build().observeForever(data::postValue)
        }
    }

    override fun searchTvShow(
            query: String,
            callback: MutableLiveData<TvShowState>,
            data: MutableLiveData<PagedList<DataTvShow>>
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            LivePagedListBuilder(
                    factory.searchTvDataFactory.also {
                        it.keyword = query
                        it.liveData = callback
                    },
                    config
            ).build().observeForever(data::postValue)
        }
    }

    override fun getFavoriteMovie(data: MutableLiveData<PagedList<MovieEntity>>) {
        throw UnsupportedOperationException()
    }

    override fun searchFavoriteMovie(query: String, data: MutableLiveData<PagedList<MovieEntity>>) {
        throw UnsupportedOperationException()
    }

    override fun getFavoriteTvShow(data: MutableLiveData<PagedList<TvShowEntity>>) {
        throw UnsupportedOperationException()
    }

    override fun searchFavoriteTvShow(query: String, data: MutableLiveData<PagedList<TvShowEntity>>) {
        throw UnsupportedOperationException()
    }

    override fun addDataMovie(data: MovieEntity) {
        throw UnsupportedOperationException()
    }

    override fun checkDataMovie(data: MovieEntity): List<MovieEntity> {
        throw UnsupportedOperationException()
    }

    override fun deleteDataMovie(data: MovieEntity) {
        throw UnsupportedOperationException()
    }

    override fun addDataTvShow(data: TvShowEntity) {
        throw UnsupportedOperationException()
    }

    override fun checkDataTvShow(data: TvShowEntity): List<TvShowEntity> {
        throw UnsupportedOperationException()
    }

    override fun deleteDataTvShow(data: TvShowEntity) {
        throw UnsupportedOperationException()
    }

    override fun getDatabase(): RoomDb {
        throw UnsupportedOperationException()
    }
}