package web.id.wahyou.movieq.ui.movie.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import web.id.wahyou.movieq.BuildConfig.imageUrl
import web.id.wahyou.movieq.R
import web.id.wahyou.movieq.data.mapper.MovieMapper
import web.id.wahyou.movieq.data.model.detailmovie.ResponseDetailMovie
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.databinding.ActivityDetailMovieBinding
import web.id.wahyou.movieq.databinding.BottomSheetBinding
import web.id.wahyou.movieq.state.DetailMovieState
import web.id.wahyou.movieq.ui.movie.adapter.ProductionCompanyAdapter
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

    private val adapterProduction : ProductionCompanyAdapter by lazy {
        ProductionCompanyAdapter()
    }

    private val dataLocal by lazy {
        MovieMapper.mapResponseToEntity(data!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        delay()
        setupStatusBar()
        setupView()
        setupViewModel()
        setupListener()
    }

    private fun setupViewModel() {
        viewModel.state.observe(this, {
            when(it){
                is DetailMovieState.Loading   -> getLoadingDetailMovie(true)
                is DetailMovieState.Result    -> successGetDetailMovie(it.data)
                is DetailMovieState.Error     -> showError()
            }
        })

        viewModel.stateFavorite.observe(this, {
            when (it) {
                true -> setDrawableIsFavorite()
                false -> setDrawableNotFavorite()
            }
        })

        viewModel.checkFavorite(dataLocal)
        viewModel.getDetailMovie(data!!.id)
    }

    private fun setupView() {
        with(binding) {
            rvProduction.also {
                it.adapter = adapterProduction
                it.layoutManager = LinearLayoutManager(
                        this@DetailMovieActivity,
                        LinearLayoutManager.HORIZONTAL ,false)
                it.setHasFixedSize(true)
            }
        }
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

            btnFavorite.setOnClickListener {
                viewModel.addToFavorite(dataLocal)
            }

            adapterProduction.setData(response.production_companies)
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

    private fun setDrawableIsFavorite() {
        binding.btnFavorite.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_baseline_favorite_24
            )
        )
    }

    private fun setDrawableNotFavorite() {
        binding.btnFavorite.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_baseline_favorite_border_24
            )
        )
    }

    private fun setupListener() {
        binding.imgBack.setOnClickListener {
            finish()
        }
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