package web.id.wahyou.movieq.data.factory

import web.id.wahyou.movieq.data.factory.movie.PopularMovieDataFactory
import web.id.wahyou.movieq.data.factory.movie.SearchMovieDataFactory
import web.id.wahyou.movieq.data.factory.movie.TopRatedMovieDataFactory
import web.id.wahyou.movieq.data.factory.movie.UpcomingMovieDataFactory
import web.id.wahyou.movieq.data.factory.tvshow.SearchTvDataFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class Factory  @Inject constructor(
    val upcomingMovieDataFactory: UpcomingMovieDataFactory,
    val topRatedMovieDataFactory: TopRatedMovieDataFactory,
    val popularMovieDataFactory: PopularMovieDataFactory,
    val searchMovieDataFactory: SearchMovieDataFactory,
    val searchTvDataFactory: SearchTvDataFactory
)