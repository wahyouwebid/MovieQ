package web.id.wahyou.movieq.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import web.id.wahyou.movieq.databinding.FragmentTvshowBinding
import web.id.wahyou.movieq.model.DataTvShow
import web.id.wahyou.movieq.ui.adapter.TvShowAdapter
import web.id.wahyou.movieq.ui.movie.detail.DetailMovieActivity
import web.id.wahyou.movieq.ui.tvshow.detail.DetailTvShowActivity

class TvShowFragment : Fragment() {

    private val adapter: TvShowAdapter by lazy {
        TvShowAdapter{item -> detailTvShow(item)}
    }

    private val viewModel: TvShowViewModel by lazy {
        ViewModelProviders.of(this).get(TvShowViewModel::class.java)
    }

    private val binding : FragmentTvshowBinding by lazy {
        FragmentTvshowBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupData()
    }

    private fun setupData() {
        adapter.setData(viewModel.getData())
    }

    private fun setupView() {
        with(binding) {
            rvTvShow.also {
                it.adapter = adapter
                it.layoutManager = GridLayoutManager(requireContext(), 3)
                it.setHasFixedSize(true)
            }
        }
    }

    private fun detailTvShow(item: DataTvShow) {
        startActivity(Intent(requireContext(), DetailTvShowActivity::class.java).also {
            it.putExtra("data", item)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}