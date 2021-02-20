package web.id.wahyou.movieq.ui.movie.popular

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.data.repository.Repository
import web.id.wahyou.movieq.state.MovieState

class PopularMovieViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    val state : MutableLiveData<MovieState> by lazy {
        MutableLiveData<MovieState>()
    }

    val data : MutableLiveData<PagedList<DataMovie>> by lazy {
        MutableLiveData<PagedList<DataMovie>>()
    }

    fun getMovie() {
        repository.getAllPopularMovie(state, data)
    }
}