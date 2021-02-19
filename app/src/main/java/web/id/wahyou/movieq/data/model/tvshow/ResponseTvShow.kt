package web.id.wahyou.movieq.data.model.tvshow

import com.google.gson.annotations.SerializedName

data class ResponseTvShow (
    @SerializedName("results") val data : List<DataTvShow>,
    @SerializedName("page") val page : Int,
    @SerializedName("total_pages") val total_pages : Int
)