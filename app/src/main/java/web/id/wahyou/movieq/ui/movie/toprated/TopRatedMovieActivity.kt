package web.id.wahyou.movieq.ui.movie.toprated

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.databinding.ActivityMovieTopRatedBinding
import web.id.wahyou.movieq.databinding.BottomSheetBinding
import web.id.wahyou.movieq.state.MovieState
import web.id.wahyou.movieq.ui.movie.adapter.AllMovieAdapter
import web.id.wahyou.movieq.ui.movie.detail.DetailMovieActivity

@AndroidEntryPoint
class TopRatedMovieActivity : AppCompatActivity() {

    private val binding : ActivityMovieTopRatedBinding by lazy {
        ActivityMovieTopRatedBinding.inflate(layoutInflater)
    }

    private val viewModel : TopRatedMovieViewModel by viewModels()

    private val adapter : AllMovieAdapter by lazy {
        AllMovieAdapter { item -> detailMovie(item)}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupStatusBar()
        setupView()
        getData()
        setupViewModel()
    }

    private fun setupView() {
        with(binding) {
            rvMovie.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(
                    this@TopRatedMovieActivity, LinearLayoutManager.VERTICAL, false
                )
                it.setHasFixedSize(true)
            }

            imgBack.setOnClickListener { finish() }
        }
    }

    private fun getData() {
        viewModel.getMovie()
    }

    private fun setupViewModel() {
        viewModel.state.observe(this, {
            when (it) {
                is MovieState.Loading -> getLoadingUpcoming(true)
                is MovieState.Result -> getLoadingUpcoming(false)
                is MovieState.Error -> showError()
            }
        })
        viewModel.data.observe(this, Observer(adapter::submitList))
    }

    private fun getLoadingUpcoming(loading: Boolean) {
        with(binding) {
            if (loading) {
                rvMovie.visibility = View.INVISIBLE
                shMovie.visibility = View.VISIBLE
            }else {
                rvMovie.visibility = View.VISIBLE
                shMovie.visibility = View.INVISIBLE
            }
        }
    }

    private fun showError() {
        val binding = BottomSheetBinding.inflate(layoutInflater)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(binding.root)
        with(binding) {
            btnOk.setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    private fun detailMovie(item: DataMovie) {
        startActivity(Intent(this, DetailMovieActivity::class.java).also {
            it.putExtra("data", item)
        })
    }

    private fun setupStatusBar() {
        with(window) {
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }
}