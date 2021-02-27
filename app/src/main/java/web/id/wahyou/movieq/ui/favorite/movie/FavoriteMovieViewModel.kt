package web.id.wahyou.movieq.ui.favorite.movie

import android.widget.EditText
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jakewharton.rxbinding3.widget.TextViewTextChangeEvent
import com.jakewharton.rxbinding3.widget.textChangeEvents
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import web.id.wahyou.movieq.data.database.model.MovieEntity
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.data.repository.Repository
import java.util.concurrent.TimeUnit

class FavoriteMovieViewModel @ViewModelInject constructor(
    val repository: Repository
) : ViewModel() {

    val data : MutableLiveData<PagedList<MovieEntity>> by lazy {
        MutableLiveData<PagedList<MovieEntity>>()
    }

    fun getFavoriteMovie(){
        repository.getFavoriteMovie(data)
    }

    fun setupSearch(editText: EditText){
        editText.textChangeEvents()
            .skipInitialValue()
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<TextViewTextChangeEvent>() {
                override fun onNext(t: TextViewTextChangeEvent) {
                    val keyword = t.text.toString()
                    if(keyword.trim{it <= ' '}.isNotEmpty() && keyword.trim{it <= ' '}.length >= 3) {
                        repository.searchFavoriteMovie(keyword, data)
                    }else{
                        repository.getFavoriteMovie(data)
                    }
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }
            })
            .let { return@let repository.getDisposible()::add }
    }
}