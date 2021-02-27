package web.id.wahyou.movieq.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import web.id.wahyou.movieq.data.model.detailmovie.ResponseDetailMovie
import web.id.wahyou.movieq.data.model.detailtv.ResponseDetailTv
import web.id.wahyou.movieq.data.model.movie.ResponseMovie
import web.id.wahyou.movieq.data.model.tvshow.ResponseTvShow

interface ApiService {

    //Movie
    @GET("movie/now_playing")
    fun getNowPlayingMovie() : Single<ResponseMovie>

    @GET("movie/upcoming")
    fun getUpcomingMovie() : Single<ResponseMovie>

    @GET("movie/popular")
    fun getPopularMovie() : Single<ResponseMovie>

    @GET("movie/top_rated")
    fun getTopRatedMovie() : Single<ResponseMovie>

    @GET("movie/{movieId}")
    fun getDetailMovie(
            @Path("movieId") movieId: Int
    ) : Single<ResponseDetailMovie>

    @GET("movie/{movieType}")
    fun getAllMovie(
        @Path("movieType") movieType: String,
        @Query("page") page : Int
    ) : Single<ResponseMovie>

    @GET("search/movie")
    fun searchMovie(
            @Query("query") query: String,
            @Query("page") page : Int
    ) : Single<ResponseMovie>

    //TV Show
    @GET("tv/airing_today")
    fun getAiringTodayTvShow() : Single<ResponseTvShow>

    @GET("tv/{tvId}")
    fun getDetailTvShow(
            @Path("tvId") tvId: Int
    ) : Single<ResponseDetailTv>

    @GET("tv/top_rated")
    fun getTopRatedTvShow() : Single<ResponseTvShow>

    @GET("tv/popular")
    fun getPopularTvShow() : Single<ResponseTvShow>

    @GET("tv/{TvType}")
    fun getAllTvShow(
            @Path("TvType") TvType: String,
            @Query("page") page : Int
    ) : Single<ResponseTvShow>

    @GET("search/tv")
    fun searchTvShow(
            @Query("query") query: String,
            @Query("page") page : Int
    ) : Single<ResponseTvShow>

}