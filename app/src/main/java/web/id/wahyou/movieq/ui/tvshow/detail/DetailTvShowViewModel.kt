package web.id.wahyou.movieq.ui.tvshow.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import web.id.wahyou.movieq.data.repository.Repository
import web.id.wahyou.movieq.state.DetailTvShowState

class DetailTvShowViewModel @ViewModelInject constructor(
        private val repository: Repository
): ViewModel() {

    val state : MutableLiveData<DetailTvShowState> by lazy {
        MutableLiveData<DetailTvShowState>()
    }

    fun getDetailTvShow(tvId: Int) {
        repository.getDetailTvShow(tvId, state)
    }
}