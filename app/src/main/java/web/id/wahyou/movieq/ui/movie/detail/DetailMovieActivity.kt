package web.id.wahyou.movieq.ui.movie.detail

import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.bumptech.glide.Glide
import web.id.wahyou.movieq.BuildConfig.imageUrl
import web.id.wahyou.movieq.R
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.databinding.ActivityDetailMovieBinding
import web.id.wahyou.movieq.utils.Utils.dateFormat

class DetailMovieActivity : AppCompatActivity() {
    private val binding : ActivityDetailMovieBinding by lazy {
        ActivityDetailMovieBinding.inflate(layoutInflater)
    }

    private val data : DataMovie? by lazy {
        intent.getParcelableExtra("data")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupStatusBar()
        setData()
    }

    private fun setupStatusBar() {
        with(window) {
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        with(binding) {
            if(data!=null) {
                tvTitle.text = data?.title
                tvRelease.text = dateFormat(
                    data?.release_date!!,
                    "yyyy-mm-dd",
                    "dd MMMM yyyy"
                )
                tvPopularity.text = data?.popularity.toString() + getString(R.string.title_viewers)
                tvRating.text = data?.vote_average.toString()
                tvDescription.text = data?.overview

                Glide.with(this@DetailMovieActivity)
                    .load(imageUrl + data?.poster_path)
                    .into(imgPoster)

                Glide.with(this@DetailMovieActivity)
                    .load( imageUrl + data?.backdrop_path)
                    .into(imgBackground)
            }

            imgBack.setOnClickListener {
                finish()
            }
        }
    }
}