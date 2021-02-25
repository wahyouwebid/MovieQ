package web.id.wahyou.movieq.ui.search.movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.databinding.ActivitySearchMovieBinding
import web.id.wahyou.movieq.state.MovieState
import web.id.wahyou.movieq.ui.movie.detail.DetailMovieActivity

@AndroidEntryPoint
class SearchMovieActivity : AppCompatActivity() {

    private val binding : ActivitySearchMovieBinding by lazy {
        ActivitySearchMovieBinding.inflate(layoutInflater)
    }

    private val adapter : SearchMovieAdapter by lazy {
        SearchMovieAdapter { item -> detailMovie(item)}
    }

    private val movieViewModel : SearchMovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupStatusBar()
        setupView()
        setupViewModel()
    }

    private fun setupViewModel() {
        movieViewModel.state.observe(this, {
            when (it) {
                is MovieState.Loading -> getLoading(true)
                is MovieState.Result -> getLoading(false)
                is MovieState.Error -> showError()
            }
        })

        movieViewModel.data.observe(this, Observer(adapter::submitList))
        movieViewModel.setupSearchMovie(binding.etSearch)
    }

    private fun setupView() {
        with(binding) {
            imgBack.setOnClickListener { finish() }
            shMovie.visibility = View.INVISIBLE
            rvMovie.also {
                it.adapter = adapter
                it.layoutManager = GridLayoutManager(this@SearchMovieActivity, 1)
                it.setHasFixedSize(true)
            }
        }
    }

    private fun getLoading(loading: Boolean) {
        with(binding) {
            if (loading) {
                rvMovie.visibility = View.GONE
                shMovie.visibility = View.VISIBLE
                lotEmpty.visibility = View.GONE
            }else {
                rvMovie.visibility = View.VISIBLE
                shMovie.visibility = View.GONE
                lotEmpty.visibility = View.GONE
            }
        }
    }

    private fun showError() {
        with(binding) {
            rvMovie.visibility = View.GONE
            shMovie.visibility = View.GONE
            lotEmpty.visibility = View.VISIBLE
        }
    }

    private fun detailMovie(item: DataMovie) {
        startActivity(Intent(this, DetailMovieActivity::class.java).also {
            it.putExtra("data", item)
        })
    }

    private fun setupStatusBar() {
        with(window){
            clearFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                            or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            }
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            navigationBarColor = ContextCompat.getColor(this@SearchMovieActivity, android.R.color.white)
            statusBarColor = ContextCompat.getColor(this@SearchMovieActivity, android.R.color.white)
        }
    }
}