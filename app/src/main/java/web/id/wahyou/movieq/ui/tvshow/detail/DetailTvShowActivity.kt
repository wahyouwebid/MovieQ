package web.id.wahyou.movieq.ui.tvshow.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import web.id.wahyou.movieq.BuildConfig.imageUrl
import web.id.wahyou.movieq.R
import web.id.wahyou.movieq.data.mapper.TvShowMapper
import web.id.wahyou.movieq.data.model.detailtv.ResponseDetailTv
import web.id.wahyou.movieq.data.model.tvshow.DataTvShow
import web.id.wahyou.movieq.databinding.ActivityDetailTvShowBinding
import web.id.wahyou.movieq.databinding.BottomSheetBinding
import web.id.wahyou.movieq.state.DetailTvShowState
import web.id.wahyou.movieq.utils.Utils
import web.id.wahyou.movieq.utils.Utils.delay

@AndroidEntryPoint
class DetailTvShowActivity : AppCompatActivity() {

    private val binding : ActivityDetailTvShowBinding by lazy {
        ActivityDetailTvShowBinding.inflate(layoutInflater)
    }

    private val viewModel: DetailTvShowViewModel by viewModels()

    private val data : DataTvShow? by lazy {
        intent.getParcelableExtra("data")
    }

    private val dataLocal by lazy {
        TvShowMapper.mapResponseToEntity(data!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        delay()
        setupStatusBar()
        setupViewModel()
        setupListener()
    }

    private fun setupViewModel() {
        viewModel.state.observe(this, {
            when(it){
                is DetailTvShowState.Loading   -> getLoadingDetailTv(true)
                is DetailTvShowState.Result    -> successGetDetailTv(it.data)
                is DetailTvShowState.Error     -> showError()
            }
        })

        viewModel.stateFavorite.observe(this, {
            when (it) {
                true -> setDrawableIsFavorite()
                false -> setDrawableNotFavorite()
            }
        })

        viewModel.checkFavorite(dataLocal)
        data?.let { viewModel.getDetailTvShow(it.id) }
    }

    @SuppressLint("SetTextI18n")
    private fun successGetDetailTv(response: ResponseDetailTv) {
        getLoadingDetailTv(false)

        with(binding) {
            tvTitle.text = response.name
            tvRelease.text = Utils.dateFormat(
                    data?.first_air_date!!,
                    "yyyy-mm-dd",
                    "dd MMMM yyyy"
            )
            tvPopularity.text = response.popularity.toString() + getString(R.string.title_viewers)
            tvRating.text = data?.vote_average.toString()
            tvDescription.text = data?.overview

            Glide.with(this@DetailTvShowActivity)
                    .load(imageUrl + response.poster_path)
                    .into(imgPoster)

            Glide.with(this@DetailTvShowActivity)
                    .load(imageUrl + response.backdrop_path)
                    .into(imgBackground)

            btnFavorite.setOnClickListener {
                viewModel.addToFavorite(dataLocal)
            }
        }
    }

    private fun getLoadingDetailTv(loading: Boolean) {
        delay()
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