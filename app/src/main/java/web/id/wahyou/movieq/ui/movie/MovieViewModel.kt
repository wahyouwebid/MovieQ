package web.id.wahyou.movieq.ui.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import web.id.wahyou.movieq.data.repository.Repository
import web.id.wahyou.movieq.state.MovieState

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

class MovieViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    val stateUpcoming : MutableLiveData<MovieState> by lazy {
        MutableLiveData<MovieState>()
    }

    val statePopular : MutableLiveData<MovieState> by lazy {
        MutableLiveData<MovieState>()
    }

    val stateTopRated : MutableLiveData<MovieState> by lazy {
        MutableLiveData<MovieState>()
    }

    fun getUpcomingMovie() {
        repository.getUpcomingMovie(stateUpcoming)
    }

    fun getPopularMovie() {
        repository.getPopularMovie(statePopular)
    }

    fun getTopRatedMovie() {
        repository.getTopRatedMovie(stateTopRated)
    }
}