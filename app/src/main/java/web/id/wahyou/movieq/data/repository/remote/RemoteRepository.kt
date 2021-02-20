package web.id.wahyou.movieq.data.repository.remote

import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import web.id.wahyou.movieq.data.network.ApiService
import web.id.wahyou.movieq.data.repository.Repository
import web.id.wahyou.movieq.state.DetailMovieState
import web.id.wahyou.movieq.state.DetailTvShowState
import web.id.wahyou.movieq.state.MovieState
import web.id.wahyou.movieq.state.TvShowState
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val apiService: ApiService
) : Repository {

    var disposable: CompositeDisposable = CompositeDisposable()

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

    override fun getTvShow(callback: MutableLiveData<TvShowState>) {
        apiService.getTvShow()
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

    override fun getDisposible(): CompositeDisposable = disposable
}