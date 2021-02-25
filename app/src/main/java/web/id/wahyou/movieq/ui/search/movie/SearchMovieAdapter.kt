package web.id.wahyou.movieq.ui.search.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import web.id.wahyou.movieq.BuildConfig
import web.id.wahyou.movieq.R
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.databinding.AdapterMovieBinding
import web.id.wahyou.movieq.utils.Constant.Genres
import web.id.wahyou.movieq.utils.Utils


class SearchMovieAdapter (
    private val showDetail: (DataMovie) -> Unit
) : PagedListAdapter<DataMovie, SearchMovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            tvTitle.text = getItem(position)?.title

            tvRating.text = getItem(position)?.vote_average.toString()

            tvRelease.text = getItem(position)?.release_date?.let {
                Utils.dateFormat(
                    it,
                    "yyyy-mm-dd",
                    "yyyy"
                )
            }

            if(getItem(position)?.genreIds?.isNotEmpty() == true) {
                tvGenres.text = getItem(position)?.genreIds?.get(0)?.let { Genres.getValue(it) }
            } else {
                tvGenres.visibility = View.GONE
            }

            holder.itemView.also {
                Glide.with(it.context)
                    .load( BuildConfig.imageUrl + getItem(position)?.poster_path)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgPoster)

                tvPopularity.text = getItem(position)?.popularity.toString() +
                        it.context.getString(R.string.title_viewers)
            }

            root.setOnClickListener {
                getItem(position)?.let { it1 -> showDetail(it1) }
            }
        }
    }

    companion object{
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<DataMovie>(){
            override fun areContentsTheSame(oldItem: DataMovie, newItem: DataMovie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: DataMovie, newItem: DataMovie): Boolean {
                return oldItem == newItem
            }
        }
    }
    class ViewHolder(val view: AdapterMovieBinding) : RecyclerView.ViewHolder(view.root)
}