package web.id.wahyou.movieq.data.source.movie

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.reactivex.disposables.CompositeDisposable
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.data.network.ApiService
import web.id.wahyou.movieq.state.MovieState
import web.id.wahyou.movieq.utils.Constant.UPCOMING
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpcomingMovieDataSource @Inject constructor(
    private val endpoint: ApiService
) : PageKeyedDataSource<Int, DataMovie>() {

    lateinit var liveData: MutableLiveData<MovieState>
    
    private val disposable by lazy {
        CompositeDisposable()
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, DataMovie>
    ) {
        endpoint.getAllMovie(UPCOMING, 1)
            .map<MovieState>{
                callback.onResult(it.data.toMutableList(), 1, 2)
                MovieState.Result(it)
            }
            .onErrorReturn(MovieState::Error)
            .toFlowable()
            .startWith(MovieState.Loading)
            .subscribe(liveData::postValue)
            .let { return@let disposable::add }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DataMovie>) {
        endpoint.getAllMovie(UPCOMING, params.key)
            .map<MovieState>{
                callback.onResult(it.data.toMutableList(), params.key + 1)
                MovieState.Result(it)
            }
            .onErrorReturn(MovieState::Error)
            .toFlowable()
            .subscribe(liveData::postValue)
            .let { return@let disposable::add }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DataMovie>) {

    }
}