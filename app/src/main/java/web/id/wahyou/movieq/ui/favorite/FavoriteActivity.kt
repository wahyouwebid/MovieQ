package web.id.wahyou.movieq.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import web.id.wahyou.movieq.R
import web.id.wahyou.movieq.databinding.ActivityFavoriteBinding
import web.id.wahyou.movieq.databinding.TabLayoutBinding
import web.id.wahyou.movieq.ui.favorite.movie.FavoriteMovieFragment
import web.id.wahyou.movieq.ui.favorite.tvshow.FavoriteTvShowFragment

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private val binding : ActivityFavoriteBinding by lazy {
        ActivityFavoriteBinding.inflate(layoutInflater)
    }

    private val adapter : TabPagerAdapter by lazy {
        TabPagerAdapter(this, arrayListOf(FavoriteMovieFragment(), FavoriteTvShowFragment()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupAdapter()
        setupSensitivity()
        setupListener()
    }

    private fun setupAdapter() {
        binding.pager.adapter = adapter
        TabLayoutMediator(binding.tab, binding.pager) { tab, position ->
            when (position) {
                0 -> { tab.customView = getTabLayout(getString(R.string.title_movie), R.drawable.ic_baseline_movie_24_white) }
                1 -> { tab.customView = getTabLayout(getString(R.string.title_tvshow), R.drawable.ic_baseline_live_tv_24_white) }
            }
        }.attach()
        binding.pager.setCurrentItem(0, true)
    }

    private fun getTabLayout(title: String, icon: Int): View {
        val tabBinding = TabLayoutBinding.inflate(layoutInflater)
        tabBinding.title.text = title
        tabBinding.icon.setImageResource(icon)
        return tabBinding.root
    }

    private fun setupListener() {
        with(binding) {
            imgBack.setOnClickListener { finish() }
        }
    }

    private fun setupSensitivity() {
        try {
            val ff =
                    ViewPager2::class.java.getDeclaredField("mRecyclerView")
            ff.isAccessible = true
            val recyclerView = ff[binding.pager] as RecyclerView
            val touchSlopField =
                    RecyclerView::class.java.getDeclaredField("mTouchSlop")
            touchSlopField.isAccessible = true
            val touchSlop = touchSlopField[recyclerView] as Int
            touchSlopField[recyclerView] = touchSlop * 4
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }
}