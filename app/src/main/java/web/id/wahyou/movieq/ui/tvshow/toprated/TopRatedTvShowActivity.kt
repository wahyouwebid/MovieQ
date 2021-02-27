package web.id.wahyou.movieq.ui.tvshow.toprated

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import web.id.wahyou.movieq.R
import web.id.wahyou.movieq.databinding.ActivityTopRatedTvBinding

class TopRatedTvShowActivity : AppCompatActivity() {

    private val binding : ActivityTopRatedTvBinding by lazy {
        ActivityTopRatedTvBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}