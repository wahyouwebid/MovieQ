package web.id.wahyou.movieq.data.model.tvshow

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataTvShow (
    @SerializedName("backdrop_path") val backdrop_path : String?,
    @SerializedName("id") val id : Int,
    @SerializedName("overview") val overview : String?,
    @SerializedName("poster_path") val poster_path : String?,
    @SerializedName("name") val name : String?,
    @SerializedName("first_air_date") val first_air_date : String?,
    @SerializedName("vote_average") val vote_average : Float?,
    @SerializedName("popularity") val popularity : Double?
) : Parcelable