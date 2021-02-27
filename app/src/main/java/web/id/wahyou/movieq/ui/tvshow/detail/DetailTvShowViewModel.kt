package web.id.wahyou.movieq.ui.tvshow.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import web.id.wahyou.movieq.data.database.model.MovieEntity
import web.id.wahyou.movieq.data.database.model.TvShowEntity
import web.id.wahyou.movieq.data.repository.Repository
import web.id.wahyou.movieq.state.DetailTvShowState

class DetailTvShowViewModel @ViewModelInject constructor(
        private val repository: Repository
): ViewModel() {

    val state : MutableLiveData<DetailTvShowState> by lazy {
        MutableLiveData<DetailTvShowState>()
    }

    val stateFavorite : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun getDetailTvShow(tvId: Int) {
        repository.getDetailTvShow(tvId, state)
    }

    fun addToFavorite(data : TvShowEntity){
        CoroutineScope(Dispatchers.IO).launch {
            val checkData = async { repository.checkDataTvShow(data) }
            if(checkData.await().isEmpty()){
                repository.addDataTvShow(data)
                withContext(Dispatchers.Main){
                    stateFavorite.postValue(true)
                }
            }else{
                repository.deleteDataTvShow(data)
                withContext(Dispatchers.Main){
                    stateFavorite.postValue(false)
                }
            }
        }
    }

    fun checkFavorite(data : TvShowEntity){
        CoroutineScope(Dispatchers.IO).launch {
            val checkData = async { repository.checkDataTvShow(data) }
            if(checkData.await().isNotEmpty()){
                withContext(Dispatchers.Main){
                    stateFavorite.postValue(true)
                }
            }else{
                withContext(Dispatchers.Main){
                    stateFavorite.postValue(false)
                }
            }
        }
    }
}