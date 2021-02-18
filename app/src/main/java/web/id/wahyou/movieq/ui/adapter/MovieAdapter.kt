package web.id.wahyou.movieq.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import web.id.wahyou.movieq.databinding.AdapterMovieBinding
import web.id.wahyou.movieq.model.DataMovie

class MovieAdapter (
    private val showDetail: (DataMovie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var data = ArrayList<DataMovie>()

    fun setData(movieList: List<DataMovie>?) {
        if (movieList == null) return
        data.clear()
        data.addAll(movieList)
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            tvTitle.text = data[position].title
            tvRating.text = data[position].vote_average
            tvPopularity.text = data[position].popularity + " Viewers"

            holder.itemView.also {
                val poster = it.context.resources.getIdentifier(
                    "web.id.wahyou.movieq:drawable/"
                            + data[position].poster.substring(10, data[position].poster.length),
                    null, null
                )
                Glide.with(it.context).load(poster).into(imgPoster)
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