package web.id.wahyou.movieq.ui.movie.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import web.id.wahyou.movieq.BuildConfig.imageUrl
import web.id.wahyou.movieq.R
import web.id.wahyou.movieq.data.model.detailmovie.ResponseDetailMovie
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.databinding.ActivityDetailMovieBinding
import web.id.wahyou.movieq.databinding.BottomSheetBinding
import web.id.wahyou.movieq.state.DetailMovieState
import web.id.wahyou.movieq.utils.EspressoIdlingResource
import web.id.wahyou.movieq.utils.Utils.dateFormat
import web.id.wahyou.movieq.utils.Utils.delay

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private val binding : ActivityDetailMovieBinding by lazy {
        ActivityDetailMovieBinding.inflate(layoutInflater)
    }

    private val viewModel: DetailMovieViewModel by viewModels()

    private val data : DataMovie? by lazy {
        intent.getParcelableExtra("data")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        delay()
        setupStatusBar()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.state.observe(this, {
            when(it){
                is DetailMovieState.Loading   -> getLoadingDetailMovie(true)
                is DetailMovieState.Result    -> successGetDetailMovie(it.data)
                is DetailMovieState.Error     -> showError()
            }
        })

        data?.let { viewModel.getDetailMovie(it.id) }
    }

    @SuppressLint("SetTextI18n")
    private fun successGetDetailMovie(response: ResponseDetailMovie) {
        getLoadingDetailMovie(false)
        with(binding) {
            tvTitle.text = response.title
            tvRelease.text = dateFormat(
                    response.release_date,
                    "yyyy-mm-dd",
                    "dd MMMM yyyy"
            )
            tvPopularity.text = response.popularity.toString() + getString(R.string.title_viewers)
            tvRating.text = response.vote_average.toString()
            tvDescription.text = response.overview

            Glide.with(this@DetailMovieActivity)
                    .load(imageUrl + response.poster_path)
                    .into(imgPoster)

            Glide.with(this@DetailMovieActivity)
                    .load( imageUrl + response.backdrop_path)
                    .into(imgBackground)

            imgBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun getLoadingDetailMovie(loading: Boolean) {
        with(binding) {
            if (loading) {
                pgLoading.visibility = View.VISIBLE
            } else {
                pgLoading.visibility = View.GONE
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

    private fun setupStatusBar() {
        with(window) {
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }
}