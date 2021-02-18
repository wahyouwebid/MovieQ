package web.id.wahyou.movieq.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataMovie(
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val popularity: String,
    val vote_average: String,
    val poster: String
) : Parcelable