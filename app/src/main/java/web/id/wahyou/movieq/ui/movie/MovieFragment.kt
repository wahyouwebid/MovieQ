package web.id.wahyou.movieq.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.databinding.BottomSheetBinding
import web.id.wahyou.movieq.databinding.FragmentMovieBinding
import web.id.wahyou.movieq.state.MovieState
import web.id.wahyou.movieq.ui.movie.detail.DetailMovieActivity

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModels()

    private val binding : FragmentMovieBinding by lazy {
        FragmentMovieBinding.inflate(layoutInflater)
    }

    private val adapter: MovieAdapter by lazy {
        MovieAdapter{item -> detailMovie(item)}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.state.observe(viewLifecycleOwner, {
            when(it){
                is MovieState.Loading   -> getLoading(true)
                is MovieState.Result    -> successGetData(it.data.data)
                is MovieState.Error     -> showError()
            }
        })
        viewModel.getMovie()
    }

    private fun setupView() {
        with(binding) {
            rvMovie.also {
                it.adapter = adapter
                it.layoutManager = GridLayoutManager(requireContext(), 1)
                it.setHasFixedSize(true)
            }
        }
    }

    private fun successGetData(data : List<DataMovie>) {
        getLoading(false)
        adapter.setData(data)
    }

    private fun getLoading(loading: Boolean) {
        with(binding) {
            if (loading) {
                rvMovie.visibility = View.GONE
                shMovie.visibility = View.VISIBLE
            }else {
                rvMovie.visibility = View.VISIBLE
                shMovie.visibility = View.GONE
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