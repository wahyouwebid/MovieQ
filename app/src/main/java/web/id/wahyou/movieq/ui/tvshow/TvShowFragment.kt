package web.id.wahyou.movieq.ui.tvshow

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
import web.id.wahyou.movieq.data.model.tvshow.DataTvShow
import web.id.wahyou.movieq.databinding.BottomSheetBinding
import web.id.wahyou.movieq.databinding.FragmentTvshowBinding
import web.id.wahyou.movieq.state.TvShowState
import web.id.wahyou.movieq.ui.search.tvshow.SearchTvShowActivity
import web.id.wahyou.movieq.ui.tvshow.adapter.HorizontalTvAdapter
import web.id.wahyou.movieq.ui.tvshow.adapter.VerticalTvAdapter
import web.id.wahyou.movieq.ui.tvshow.detail.DetailTvShowActivity
import web.id.wahyou.movieq.utils.Utils.delay

@AndroidEntryPoint
class TvShowFragment : Fragment() {

    private val viewModel: TvShowViewModel by viewModels()

    private val binding : FragmentTvshowBinding by lazy {
        FragmentTvshowBinding.inflate(layoutInflater)
    }

    private val adapterAiringToday: HorizontalTvAdapter by lazy {
        HorizontalTvAdapter{ item -> detailTvShow(item)}
    }

    private val adapterTopRated: HorizontalTvAdapter by lazy {
        HorizontalTvAdapter{ item -> detailTvShow(item)}
    }

    private val adapterPopular: VerticalTvAdapter by lazy {
        VerticalTvAdapter{ item -> detailTvShow(item)}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        delay()
        setupView()
        setupViewModel()
        setupData()
    }

    private fun setupData(){
        viewModel.getAiringTodayTvShow()
        viewModel.getTopRatedTvShow()
        viewModel.getPopularTvShow()
    }

    private fun setupViewModel() {
        viewModel.stateAiringToday.observe(viewLifecycleOwner, {
            when(it) {
                is TvShowState.Loading -> getLoadingAiringToday(true)
                is TvShowState.Result -> successGetDataAiringToday(it.data.data)
                is TvShowState.Error -> showError()
            }
        })

        viewModel.stateTopRated.observe(viewLifecycleOwner, {
            when(it) {
                is TvShowState.Loading -> getLoadingTopRated(true)
                is TvShowState.Result -> successGetDataTopRated(it.data.data)
                is TvShowState.Error -> showError()
            }
        })

        viewModel.statePopular.observe(viewLifecycleOwner, {
            when(it) {
                is TvShowState.Loading -> getLoadingPopular(true)
                is TvShowState.Result -> successGetDataPopular(it.data.data)
                is TvShowState.Error -> showError()
            }
        })
    }

    private fun setupView() {
        with(binding) {
            rvAiringToday.also {
                it.adapter = adapterAiringToday
                it.layoutManager = LinearLayoutManager(
                        requireContext(), LinearLayoutManager.HORIZONTAL ,false)
                it.setHasFixedSize(true)
            }

            rvTopRated.also {
                it.adapter = adapterTopRated
                it.layoutManager = LinearLayoutManager(
                        requireContext(), LinearLayoutManager.HORIZONTAL ,false)
                it.setHasFixedSize(true)
            }

            rvPopular.also {
                it.adapter = adapterPopular
                it.layoutManager = GridLayoutManager(requireContext(), 3)
                it.setHasFixedSize(true)
            }

            search.setOnClickListener {
                startActivity(Intent(requireContext(), SearchTvShowActivity::class.java))
            }
        }
    }

    private fun successGetDataAiringToday(data: List<DataTvShow>) {
        getLoadingAiringToday(false)
        adapterAiringToday.setData(data)
    }

    private fun successGetDataTopRated(data: List<DataTvShow>) {
        getLoadingTopRated(false)
        adapterTopRated.setData(data)
    }

    private fun successGetDataPopular(data: List<DataTvShow>) {
        getLoadingPopular(false)
        adapterPopular.setData(data)
    }

    private fun getLoadingAiringToday(isloading: Boolean) {
        with(binding){
            if (isloading) {
                shAiringToday.visibility = View.VISIBLE
                rvAiringToday.visibility = View.INVISIBLE
            } else {
                shAiringToday.visibility = View.INVISIBLE
                rvAiringToday.visibility = View.VISIBLE
            }
        }
    }

    private fun getLoadingTopRated(isloading: Boolean) {
        with(binding){
            if (isloading) {
                shTopRated.visibility = View.VISIBLE
                rvTopRated.visibility = View.INVISIBLE
            } else {
                shTopRated.visibility = View.INVISIBLE
                rvTopRated.visibility = View.VISIBLE
            }
        }
    }

    private fun getLoadingPopular(isloading: Boolean) {
        with(binding){
            if (isloading) {
                shPopular.visibility = View.VISIBLE
                rvPopular.visibility = View.INVISIBLE
            } else {
                shPopular.visibility = View.INVISIBLE
                rvPopular.visibility = View.VISIBLE
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