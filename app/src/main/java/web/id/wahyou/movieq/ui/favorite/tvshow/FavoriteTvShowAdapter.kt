package web.id.wahyou.movieq.ui.favorite.tvshow

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import web.id.wahyou.movieq.data.database.model.TvShowEntity
import web.id.wahyou.movieq.databinding.AdapterVerticalTvshowBinding

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

class FavoriteTvShowAdapter (
    private val showDetail: (TvShowEntity) -> Unit
) : PagedListAdapter<TvShowEntity, FavoriteTvShowAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
            AdapterVerticalTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            tvTitle.text = getItem(position)?.name

            holder.itemView.also {
                Glide.with(it.context)
                    .load( "https://image.tmdb.org/t/p/w600_and_h900_bestv2/" + getItem(position)?.poster_path)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgPoster)
            }

            root.setOnClickListener {
                getItem(position)?.let { it1 -> showDetail(it1) }
            }
        }
    }

    companion object{
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<TvShowEntity>(){
            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
    class ViewHolder(val view: AdapterVerticalTvshowBinding) : RecyclerView.ViewHolder(view.root)
}