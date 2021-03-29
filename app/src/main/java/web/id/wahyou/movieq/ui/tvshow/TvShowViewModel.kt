package web.id.wahyou.movieq.ui.tvshow

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import web.id.wahyou.movieq.data.repository.Repository
import web.id.wahyou.movieq.state.TvShowState

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

class TvShowViewModel @ViewModelInject constructor(
    val repository: Repository
) : ViewModel() {

    val stateAiringToday : MutableLiveData<TvShowState> by lazy {
        MutableLiveData<TvShowState>()
    }

    val stateTopRated : MutableLiveData<TvShowState> by lazy {
        MutableLiveData<TvShowState>()
    }

    val statePopular : MutableLiveData<TvShowState> by lazy {
        MutableLiveData<TvShowState>()
    }

    fun getAiringTodayTvShow() {
        repository.getAiringTodayTvShow(stateAiringToday)
    }

    fun getTopRatedTvShow() {
        repository.getTopRatedTvShow(stateTopRated)
    }

    fun getPopularTvShow() {
        repository.getPopularTvShow(statePopular)
    }
}