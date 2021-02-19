package web.id.wahyou.movieq.data.network

import io.reactivex.Single
import retrofit2.http.GET
import web.id.wahyou.movieq.data.model.movie.ResponseMovie
import web.id.wahyou.movieq.data.model.tvshow.ResponseTvShow

interface ApiService {

    //Movie
    @GET("movie/upcoming")
    fun getUpcomingMovie() : Single<ResponseMovie>

    //TV Show
    @GET("tv/airing_today")
    fun getTvShow() : Single<ResponseTvShow>
}