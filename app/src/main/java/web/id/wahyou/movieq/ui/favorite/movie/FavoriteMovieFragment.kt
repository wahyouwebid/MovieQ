package web.id.wahyou.movieq.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import web.id.wahyou.movieq.data.database.model.MovieEntity
import web.id.wahyou.movieq.data.mapper.MovieMapper
import web.id.wahyou.movieq.databinding.FragmentFavoriteMovieBinding
import web.id.wahyou.movieq.ui.movie.detail.DetailMovieActivity
import web.id.wahyou.movieq.utils.Utils.sheetBehavior

@AndroidEntryPoint
class FavoriteMovieFragment : Fragment() {

    private val binding : FragmentFavoriteMovieBinding by lazy {
        FragmentFavoriteMovieBinding.inflate(layoutInflater)
    }

    private val adapter : FavoriteMovieAdapter by lazy {
        FavoriteMovieAdapter {item -> detailMovie(item)}
    }

    private val viewModel : FavoriteMovieViewModel by viewModels()

    private lateinit var behavior: BottomSheetBehavior<*>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomsheet()
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        with(binding){
            rvMovie.also {
                it.adapter = adapter
                it.layoutManager = GridLayoutManager(requireContext(), 1)
            }
        }
    }

    private fun setupViewModel() {
        viewModel.data.observe(viewLifecycleOwner, Observer(adapter::submitList))
        viewModel.getFavoriteMovie()
        viewModel.setupSearch(binding.etSearch)
    }

    private fun setupBottomsheet(){
        behavior = binding.bottomSheet.sheetBehavior()
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        behavior.peekHeight = height/2
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun detailMovie(item: MovieEntity) {
        val dataMovie = MovieMapper.mapEntityToResponse(item)
        startActivity(Intent(requireContext(), DetailMovieActivity::class.java).also {
            it.putExtra("data", dataMovie)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}