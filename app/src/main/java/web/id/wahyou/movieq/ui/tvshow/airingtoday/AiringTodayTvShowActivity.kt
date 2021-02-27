package web.id.wahyou.movieq.ui.tvshow.airingtoday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import web.id.wahyou.movieq.R
import web.id.wahyou.movieq.databinding.ActivityAiringTodayTvBinding

class AiringTodayTvShowActivity : AppCompatActivity() {

    private val binding : ActivityAiringTodayTvBinding by lazy {
        ActivityAiringTodayTvBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}