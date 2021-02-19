package web.id.wahyou.movieq.ui.tvshow

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import web.id.wahyou.movieq.data.repository.Repository
import web.id.wahyou.movieq.state.TvShowState

class TvShowViewModel @ViewModelInject constructor(
    val repository: Repository
) : ViewModel() {

    val state : MutableLiveData<TvShowState> by lazy {
        MutableLiveData<TvShowState>()
    }

    fun getTvShow() {
        repository.getTvShow(state)
    }
}