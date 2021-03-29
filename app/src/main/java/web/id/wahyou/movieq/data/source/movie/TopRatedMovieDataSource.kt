package web.id.wahyou.movieq.data.source.movie

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.reactivex.disposables.CompositeDisposable
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.data.network.ApiService
import web.id.wahyou.movieq.state.MovieState
import web.id.wahyou.movieq.utils.Constant.TOP_RATED
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

@Singleton
class TopRatedMovieDataSource @Inject constructor(
    private val apiService: ApiService
) : PageKeyedDataSource<Int, DataMovie>() {

    lateinit var liveData: MutableLiveData<MovieState>
    
    private val disposable by lazy {
        CompositeDisposable()
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, DataMovie>
    ) {
        apiService.getAllMovie(TOP_RATED, 1)
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
        apiService.getAllMovie(TOP_RATED, params.key)
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