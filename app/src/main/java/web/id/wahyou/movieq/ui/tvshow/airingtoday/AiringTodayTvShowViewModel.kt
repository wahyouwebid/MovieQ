package web.id.wahyou.movieq.ui.tvshow.airingtoday

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import web.id.wahyou.movieq.data.model.tvshow.DataTvShow
import web.id.wahyou.movieq.data.repository.Repository
import web.id.wahyou.movieq.state.TvShowState

class AiringTodayTvShowViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    val state : MutableLiveData<TvShowState> by lazy {
        MutableLiveData<TvShowState>()
    }

    val data : MutableLiveData<PagedList<DataTvShow>> by lazy {
        MutableLiveData<PagedList<DataTvShow>>()
    }

    fun getAiringTodayTvShow() {
        repository.getAllAiringTodayTvShow(state, data)
    }
}