package web.id.wahyou.movieq.ui.movie.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import web.id.wahyou.movieq.data.repository.Repository
import web.id.wahyou.movieq.state.DetailMovieState

class DetailMovieViewModel @ViewModelInject constructor(
        private val repository: Repository
): ViewModel() {

    val state : MutableLiveData<DetailMovieState> by lazy {
        MutableLiveData<DetailMovieState>()
    }

    fun getDetailMovie(movieId: Int) {
        repository.getDetailMovie(movieId, state)
    }
}