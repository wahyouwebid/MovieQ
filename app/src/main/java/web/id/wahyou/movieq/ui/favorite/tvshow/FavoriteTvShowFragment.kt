package web.id.wahyou.movieq.ui.favorite.tvshow

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import web.id.wahyou.movieq.databinding.FragmentFavoriteTvShowBinding
import web.id.wahyou.movieq.utils.Utils.sheetBehavior

@AndroidEntryPoint
class FavoriteTvShowFragment : Fragment() {

    private val binding : FragmentFavoriteTvShowBinding by lazy {
        FragmentFavoriteTvShowBinding.inflate(layoutInflater)
    }

    private lateinit var behavior: BottomSheetBehavior<*>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomsheet()
    }

    private fun setupBottomsheet(){
        behavior = binding.bottomSheet.sheetBehavior()
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        behavior.peekHeight = height/2
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}