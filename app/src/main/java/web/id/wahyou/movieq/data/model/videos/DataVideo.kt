package web.id.wahyou.movieq.data.model.videos

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataVideo(
        @SerializedName("id") val id: String,
        @SerializedName("iso_3166_1") val iso_3166_1: String,
        @SerializedName("iso_639_1") val iso_639_1: String,
        @SerializedName("key") val key: String,
        @SerializedName("name") val name: String,
        @SerializedName("site") val site: String,
        @SerializedName("size") val size: Int,
        @SerializedName("type") val type: String
) : Parcelable