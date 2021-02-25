package web.id.wahyou.movieq.ui.search.movie

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
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.data.repository.Repository
import web.id.wahyou.movieq.state.MovieState
import java.util.concurrent.TimeUnit

class SearchMovieViewModel @ViewModelInject constructor(
    val repository : Repository
): ViewModel() {

    val state : MutableLiveData<MovieState> by lazy {
        MutableLiveData<MovieState>()
    }

    val data : MutableLiveData<PagedList<DataMovie>> by lazy {
        MutableLiveData<PagedList<DataMovie>>()
    }

    fun setupSearchMovie(editText: EditText){
        editText.textChangeEvents()
            .skipInitialValue()
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<TextViewTextChangeEvent>() {
                override fun onNext(t: TextViewTextChangeEvent) {
                    val keyword = t.text.toString()
                    repository.searchMovie(keyword, state, data)
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }
            })
            .let { return@let repository.getDisposible()::add }
    }
}