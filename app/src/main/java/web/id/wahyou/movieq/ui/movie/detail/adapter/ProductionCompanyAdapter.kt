package web.id.wahyou.movieq.ui.movie.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import web.id.wahyou.movieq.BuildConfig.imageUrl
import web.id.wahyou.movieq.R
import web.id.wahyou.movieq.data.model.detailmovie.ProductionCompany
import web.id.wahyou.movieq.databinding.AdapterProductionCompanyBinding

class ProductionCompanyAdapter () : RecyclerView.Adapter<ProductionCompanyAdapter.ViewHolder>() {

    private var data = ArrayList<ProductionCompany>()

    fun setData(movieList: List<ProductionCompany>?) {
        if (movieList.isNullOrEmpty()) return
        data.clear()
        data.addAll(movieList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            tvTitle.text = data[position].name

            holder.itemView.also {
                if(data[position].logo_path.isNullOrEmpty()) {
                    imgPoster.setImageDrawable(
                            ContextCompat.getDrawable(
                                    it.context,
                                    R.drawable.shp_circle_grey
                            )
                    )
                } else {
                    Glide.with(it.context)
                            .load( imageUrl + data[position].logo_path)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(imgPoster)
                }
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterProductionCompanyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
        )
    )

    class ViewHolder(val view: AdapterProductionCompanyBinding) : RecyclerView.ViewHolder(view.root)

}