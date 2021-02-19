package web.id.wahyou.movieq.data.repository

import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import web.id.wahyou.movieq.state.MovieState
import web.id.wahyou.movieq.state.TvShowState

interface Repository {
    fun getUpcomingMovie(callback : MutableLiveData<MovieState>)
    fun getTvShow(callback: MutableLiveData<TvShowState>)

    fun getDisposible() : CompositeDisposable
}