package web.id.wahyou.movieq.data.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tvshow")
data class TvShowEntity (
    val backdrop_path : String?,
    @PrimaryKey
    val id : Int,
    val overview : String?,
    val poster_path : String?,
    val name : String?,
    val first_air_date : String?,
    val vote_average : Float?,
    val popularity : Double?
) : Parcelable