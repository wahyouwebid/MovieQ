package web.id.wahyou.movieq.ui.tvshow.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import web.id.wahyou.movieq.BuildConfig.imageUrl
import web.id.wahyou.movieq.data.model.tvshow.DataTvShow
import web.id.wahyou.movieq.databinding.AdapterHorizontalTvBinding
import web.id.wahyou.movieq.utils.Utils

class HorizontalTvShowAdapter (
    private val showDetail: (DataTvShow) -> Unit
) : RecyclerView.Adapter<HorizontalTvShowAdapter.ViewHolder>() {

    private var data = ArrayList<DataTvShow>()

    fun setData(movieList: List<DataTvShow>?) {
        if (movieList == null) return
        data.clear()
        data.addAll(movieList)
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            val newRating = Utils.nomalizeRating(data[position].vote_average!!.toFloat())
            tvTitle.text = data[position].name
            rating.rating = newRating

            holder.itemView.also {
                Glide.with(it.context)
                    .load(imageUrl + data[position].poster_path)
                    .into(imgPoster)
            }

            root.setOnClickListener {
                showDetail(data[position])
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterHorizontalTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterHorizontalTvBinding) : RecyclerView.ViewHolder(view.root)

}