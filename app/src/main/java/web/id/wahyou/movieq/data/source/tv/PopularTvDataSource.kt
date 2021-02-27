package web.id.wahyou.movieq.data.source.tv

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.reactivex.disposables.CompositeDisposable
import web.id.wahyou.movieq.data.model.tvshow.DataTvShow
import web.id.wahyou.movieq.data.network.ApiService
import web.id.wahyou.movieq.state.TvShowState
import web.id.wahyou.movieq.utils.Constant.POPULAR
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularTvDataSource @Inject constructor(
    private val apiService: ApiService
) : PageKeyedDataSource<Int, DataTvShow>() {

    lateinit var liveData: MutableLiveData<TvShowState>
    
    private val disposable by lazy {
        CompositeDisposable()
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, DataTvShow>
    ) {
        apiService.getAllTvShow(POPULAR, 1)
            .map<TvShowState>{
                callback.onResult(it.data.toMutableList(), 1, 2)
                TvShowState.Result(it)
            }
            .onErrorReturn(TvShowState::Error)
            .toFlowable()
            .startWith(TvShowState.Loading)
            .subscribe(liveData::postValue)
            .let { return@let disposable::add }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DataTvShow>) {
        apiService.getAllTvShow(POPULAR, params.key)
            .map<TvShowState>{
                callback.onResult(it.data.toMutableList(), params.key + 1)
                TvShowState.Result(it)
            }
            .onErrorReturn(TvShowState::Error)
            .toFlowable()
            .subscribe(liveData::postValue)
            .let { return@let disposable::add }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DataTvShow>) {

    }
}