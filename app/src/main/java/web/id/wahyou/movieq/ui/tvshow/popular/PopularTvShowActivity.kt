package web.id.wahyou.movieq.ui.tvshow.popular

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import web.id.wahyou.movieq.data.model.tvshow.DataTvShow
import web.id.wahyou.movieq.databinding.ActivityPopularTvBinding
import web.id.wahyou.movieq.databinding.BottomSheetBinding
import web.id.wahyou.movieq.state.TvShowState
import web.id.wahyou.movieq.ui.tvshow.adapter.AllTvShowAdapter
import web.id.wahyou.movieq.ui.tvshow.detail.DetailTvShowActivity

@AndroidEntryPoint
class PopularTvShowActivity : AppCompatActivity() {

    private val binding : ActivityPopularTvBinding by lazy {
        ActivityPopularTvBinding.inflate(layoutInflater)
    }

    private val viewModel : PopularTvShowViewModel by viewModels()

    private val showAdapter : AllTvShowAdapter by lazy {
        AllTvShowAdapter{ item -> detailTvShow(item)}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
        setupData()
        setupViewModel()
        setupStatusBar()
    }

    private fun setupView() {
        with(binding) {
            rvTvShow.also {
                it.adapter = showAdapter
                it.layoutManager = GridLayoutManager(this@PopularTvShowActivity, 3)
                it.setHasFixedSize(true)
            }

            imgBack.setOnClickListener { finish() }
        }
    }

    private fun setupData() {
        viewModel.getTvShow()
    }

    private fun setupViewModel() {
        viewModel.state.observe(this, {
            when (it) {
                is TvShowState.Loading -> getLoadingAiringToday(true)
                is TvShowState.Result -> getLoadingAiringToday(false)
                is TvShowState.Error -> showError()
            }
        })
        viewModel.data.observe(this, Observer(showAdapter::submitList))
    }

    private fun getLoadingAiringToday(loading: Boolean) {
        with(binding) {
            if (loading) {
                rvTvShow.visibility = View.INVISIBLE
                shTvShow.visibility = View.VISIBLE
            }else {
                rvTvShow.visibility = View.VISIBLE
                shTvShow.visibility = View.INVISIBLE
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

    private fun detailTvShow(item: DataTvShow) {
        startActivity(Intent(this, DetailTvShowActivity::class.java).also {
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