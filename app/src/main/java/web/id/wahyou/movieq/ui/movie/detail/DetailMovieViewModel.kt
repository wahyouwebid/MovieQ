package web.id.wahyou.movieq.ui.movie.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import web.id.wahyou.movieq.data.database.model.MovieEntity
import web.id.wahyou.movieq.data.repository.Repository
import web.id.wahyou.movieq.state.DetailMovieState

class DetailMovieViewModel @ViewModelInject constructor(
        private val repository: Repository
): ViewModel() {

    val state : MutableLiveData<DetailMovieState> by lazy {
        MutableLiveData<DetailMovieState>()
    }

    val stateFavorite : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun getDetailMovie(movieId: Int) {
        repository.getDetailMovie(movieId, state)
    }

    fun add(data : MovieEntity){
        CoroutineScope(Dispatchers.IO).launch {
            val checkData = async { repository.checkDataMovie(data) }
            if(checkData.await().isEmpty()){
                repository.addDataMovie(data)
                withContext(Dispatchers.Main){
                    stateFavorite.postValue(true)
                }
            }else{
                repository.deleteDataMovie(data)
                withContext(Dispatchers.Main){
                    stateFavorite.postValue(false)
                }
            }
        }
    }

    fun check(data : MovieEntity){
        CoroutineScope(Dispatchers.IO).launch {
            val checkData = async { repository.checkDataMovie(data) }
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