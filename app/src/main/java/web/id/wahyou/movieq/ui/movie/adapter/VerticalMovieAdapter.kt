package web.id.wahyou.movieq.ui.movie.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import web.id.wahyou.movieq.BuildConfig.imageUrl
import web.id.wahyou.movieq.R
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.databinding.AdapterMovieBinding

class VerticalMovieAdapter (
    private val showDetail: (DataMovie) -> Unit
) : RecyclerView.Adapter<VerticalMovieAdapter.ViewHolder>() {

    private var data = ArrayList<DataMovie>()

    fun setData(movieList: List<DataMovie>?) {
        if (movieList.isNullOrEmpty()) return
        data.clear()
        data.addAll(movieList)
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            tvTitle.text = data[position].title
            tvRating.text = data[position].vote_average.toString()

            holder.itemView.also {
                Glide.with(it.context)
                        .load( imageUrl + data[position].poster_path)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imgPoster)
                tvPopularity.text = data[position].popularity.toString() +
                        it.context.getString(R.string.title_viewers)
            }

            root.setOnClickListener {
                showDetail(data[position])
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterMovieBinding) : RecyclerView.ViewHolder(view.root)

}