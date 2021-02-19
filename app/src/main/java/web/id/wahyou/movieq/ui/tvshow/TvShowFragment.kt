package web.id.wahyou.movieq.ui.tvshow

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
import web.id.wahyou.movieq.data.model.tvshow.DataTvShow
import web.id.wahyou.movieq.databinding.BottomSheetBinding
import web.id.wahyou.movieq.databinding.FragmentTvshowBinding
import web.id.wahyou.movieq.state.TvShowState
import web.id.wahyou.movieq.ui.tvshow.detail.DetailTvShowActivity

@AndroidEntryPoint
class TvShowFragment : Fragment() {

    private val adapter: TvShowAdapter by lazy {
        TvShowAdapter{item -> detailTvShow(item)}
    }

    private val viewModel: TvShowViewModel by viewModels()

    private val binding : FragmentTvshowBinding by lazy {
        FragmentTvshowBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.state.observe(viewLifecycleOwner, {
            when(it) {
                is TvShowState.Loading -> getLoading(true)
                is TvShowState.Result -> successGetData(it.data.data)
                is TvShowState.Error -> showError()
            }
        })

        viewModel.getTvShow()
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

    private fun successGetData(data: List<DataTvShow>) {
        getLoading(false)
        adapter.setData(data)
    }

    private fun getLoading(isloading: Boolean) {
        with(binding){
            if (isloading) {
                rvTvShow.visibility = View.GONE
                shTvShow.visibility = View.VISIBLE
            } else {
                rvTvShow.visibility = View.VISIBLE
                shTvShow.visibility = View.GONE
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