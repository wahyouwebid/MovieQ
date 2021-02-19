package web.id.wahyou.movieq.ui.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import web.id.wahyou.movieq.data.repository.Repository
import web.id.wahyou.movieq.state.MovieState

class MovieViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    val state : MutableLiveData<MovieState> by lazy {
        MutableLiveData<MovieState>()
    }

    fun getMovie() {
        repository.getUpcomingMovie(state)
    }

}