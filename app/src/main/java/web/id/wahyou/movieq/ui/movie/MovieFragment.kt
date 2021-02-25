package web.id.wahyou.movieq.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.databinding.BottomSheetBinding
import web.id.wahyou.movieq.databinding.FragmentMovieBinding
import web.id.wahyou.movieq.state.MovieState
import web.id.wahyou.movieq.ui.movie.adapter.HorizontalMovieAdapter
import web.id.wahyou.movieq.ui.movie.adapter.VerticalMovieAdapter
import web.id.wahyou.movieq.ui.movie.detail.DetailMovieActivity
import web.id.wahyou.movieq.ui.movie.popular.PopularMovieActivity
import web.id.wahyou.movieq.ui.movie.toprated.TopRatedMovieActivity
import web.id.wahyou.movieq.ui.movie.upcoming.UpcomingMovieActivity
import web.id.wahyou.movieq.ui.search.movie.SearchMovieActivity
import web.id.wahyou.movieq.utils.Utils.delay

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModels()

    private val binding : FragmentMovieBinding by lazy {
        FragmentMovieBinding.inflate(layoutInflater)
    }

    private val upcomingAdapter: HorizontalMovieAdapter by lazy {
        HorizontalMovieAdapter{ item -> detailMovie(item)}
    }

    private val topRatedAdapter: HorizontalMovieAdapter by lazy {
        HorizontalMovieAdapter{ item -> detailMovie(item)}
    }

    private val popularAdapter: VerticalMovieAdapter by lazy {
        VerticalMovieAdapter{ item -> detailMovie(item)}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        delay()
        setupView()
        setupViewModel()
        setupData()
    }

    private fun setupData() {
        viewModel.getUpcomingMovie()
        viewModel.getPopularMovie()
        viewModel.getTopRatedMovie()
    }

    private fun setupViewModel() {
        viewModel.stateUpcoming.observe(viewLifecycleOwner, {
            when(it){
                is MovieState.Loading   -> getLoadingUpcoming(true)
                is MovieState.Result    -> successGetDataUpComing(it.data.data)
                is MovieState.Error     -> showError()
            }
        })

        viewModel.stateTopRated.observe(viewLifecycleOwner, {
            when(it){
                is MovieState.Loading   -> getLoadingPopular(true)
                is MovieState.Result    -> successGetDataTopRated(it.data.data)
                is MovieState.Error     -> showError()
            }
        })

        viewModel.statePopular.observe(viewLifecycleOwner, {
            when(it){
                is MovieState.Loading   -> getLoadingPopular(true)
                is MovieState.Result    -> successGetDataPopular(it.data.data)
                is MovieState.Error     -> showError()
            }
        })
    }

    private fun setupView() {
        with(binding) {
            rvUpcomingMovie.also {
                it.adapter = upcomingAdapter
                it.layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.HORIZONTAL ,false)
                it.setHasFixedSize(true)
            }

            rvTopRatedMovie.also {
                it.adapter = topRatedAdapter
                it.layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.HORIZONTAL ,false)
                it.setHasFixedSize(true)
            }

            rvPopularMovie.also {
                it.adapter = popularAdapter
                it.layoutManager = GridLayoutManager(requireContext(), 1)
                it.setHasFixedSize(true)
            }

            tvSeeUpcoming.setOnClickListener {
                startActivity(Intent(requireContext(), UpcomingMovieActivity::class.java))
            }

            tvSeeTopRated.setOnClickListener {
                startActivity(Intent(requireContext(), TopRatedMovieActivity::class.java))
            }

            tvSeePopular.setOnClickListener {
                startActivity(Intent(requireContext(), PopularMovieActivity::class.java))
            }

            search.setOnClickListener {
                startActivity(Intent(requireContext(), SearchMovieActivity::class.java))
            }
        }
    }

    private fun successGetDataUpComing(data : List<DataMovie>) {
        getLoadingUpcoming(false)
        upcomingAdapter.setData(data)
    }

    private fun successGetDataTopRated(data : List<DataMovie>) {
        getLoadingTopRated(false)
        topRatedAdapter.setData(data)
    }

    private fun successGetDataPopular(data : List<DataMovie>) {
        getLoadingPopular(false)
        popularAdapter.setData(data)
    }

    private fun getLoadingUpcoming(loading: Boolean) {
        with(binding) {
            if (loading) {
                rvUpcomingMovie.visibility = View.INVISIBLE
                shUpcomingMovie.visibility = View.VISIBLE
            }else {
                rvUpcomingMovie.visibility = View.VISIBLE
                shUpcomingMovie.visibility = View.INVISIBLE
            }
        }
    }

    private fun getLoadingTopRated(loading: Boolean) {
        with(binding) {
            if (loading) {
                rvTopRatedMovie.visibility = View.INVISIBLE
                shTopRatedMovie.visibility = View.VISIBLE
            }else {
                rvTopRatedMovie.visibility = View.VISIBLE
                shTopRatedMovie.visibility = View.INVISIBLE
            }
        }
    }

    private fun getLoadingPopular(loading: Boolean) {
        with(binding) {
            if (loading) {
                rvPopularMovie.visibility = View.INVISIBLE
                shPopular.visibility = View.VISIBLE
            }else {
                rvPopularMovie.visibility = View.VISIBLE
                shPopular.visibility = View.INVISIBLE
            }
        }
    }

    private fun showError() {
        val binding = BottomSheetBinding.inflate(layoutInflater)
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(binding.root)
        with(binding) {
            btnOk.setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    private fun detailMovie(item: DataMovie) {
        startActivity(Intent(requireContext(), DetailMovieActivity::class.java).also {
            it.putExtra("data", item)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}