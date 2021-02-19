package web.id.wahyou.movieq.data.repository

import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import web.id.wahyou.movieq.data.repository.remote.RemoteRepository
import web.id.wahyou.movieq.state.MovieState
import web.id.wahyou.movieq.state.TvShowState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(
    private val remoteRepository: RemoteRepository
) : Repository {

    override fun getUpcomingMovie(callback: MutableLiveData<MovieState>) = remoteRepository.getUpcomingMovie(callback)

    override fun getTvShow(callback: MutableLiveData<TvShowState>) = remoteRepository.getTvShow(callback)

    override fun getDisposible(): CompositeDisposable = remoteRepository.getDisposible()
}