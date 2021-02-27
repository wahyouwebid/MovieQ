package web.id.wahyou.movieq.ui.search.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import web.id.wahyou.movieq.data.model.tvshow.DataTvShow
import web.id.wahyou.movieq.databinding.ActivitySearchTvShowBinding
import web.id.wahyou.movieq.state.TvShowState
import web.id.wahyou.movieq.ui.tvshow.detail.DetailTvShowActivity

@AndroidEntryPoint
class SearchTvShowActivity : AppCompatActivity() {

    private val binding : ActivitySearchTvShowBinding by lazy {
        ActivitySearchTvShowBinding.inflate(layoutInflater)
    }

    private val adapter : SearchTvShowAdapter by lazy {
        SearchTvShowAdapter { item -> detailTvShow(item)}
    }

    private val viewModel : SearchTvShowViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
        setupViewModel()
        setupStatusBar()
    }

    private fun setupViewModel() {
        viewModel.state.observe(this, {
            when (it) {
                is TvShowState.Loading -> getLoading(true)
                is TvShowState.Result -> getLoading(false)
                is TvShowState.Error -> showError()
            }
        })

        viewModel.data.observe(this, Observer(adapter::submitList))
        viewModel.setupSearchTv(binding.etSearch)
    }

    private fun setupView() {
        with(binding) {
            imgBack.setOnClickListener { finish() }
            shTvShow.visibility = View.INVISIBLE
            rvTvShow.also {
                it.adapter = adapter
                it.layoutManager = GridLayoutManager(this@SearchTvShowActivity, 3)
                it.setHasFixedSize(true)
            }
        }
    }

    private fun getLoading(loading: Boolean) {
        with(binding) {
            if (loading) {
                rvTvShow.visibility = View.GONE
                shTvShow.visibility = View.VISIBLE
                lotEmpty.visibility = View.GONE
            }else {
                rvTvShow.visibility = View.VISIBLE
                shTvShow.visibility = View.GONE
                lotEmpty.visibility = View.GONE
            }
        }
    }

    private fun showError() {
        with(binding) {
            rvTvShow.visibility = View.GONE
            shTvShow.visibility = View.GONE
            lotEmpty.visibility = View.VISIBLE
        }
    }

    private fun detailTvShow(item: DataTvShow) {
        startActivity(Intent(this, DetailTvShowActivity::class.java).also {
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
            navigationBarColor = ContextCompat.getColor(this@SearchTvShowActivity, android.R.color.white)
            statusBarColor = ContextCompat.getColor(this@SearchTvShowActivity, android.R.color.white)
        }
    }
}