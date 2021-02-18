package web.id.wahyou.movieq.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import web.id.wahyou.movieq.databinding.AdapterTvshowBinding
import web.id.wahyou.movieq.model.DataTvShow

class TvShowAdapter (
    private val showDetail: (DataTvShow) -> Unit
) : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

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
            tvTitle.text = data[position].title

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
        AdapterTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterTvshowBinding) : RecyclerView.ViewHolder(view.root)

}