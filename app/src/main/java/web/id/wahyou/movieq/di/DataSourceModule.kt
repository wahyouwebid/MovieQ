package web.id.wahyou.movieq.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import web.id.wahyou.movieq.data.network.ApiService
import web.id.wahyou.movieq.data.source.movie.PopularMovieDataSource
import web.id.wahyou.movieq.data.source.movie.TopRatedMovieDataSource
import web.id.wahyou.movieq.data.source.movie.UpcomingMovieDataSource
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataSourceModule {
    @Provides
    @Singleton
    fun provideUpcomingMovieDataSource(
        endpoint: ApiService
    ) : UpcomingMovieDataSource = UpcomingMovieDataSource(endpoint)

    @Provides
    @Singleton
    fun provideTopRatedDataSource(
        endpoint: ApiService
    ) : TopRatedMovieDataSource = TopRatedMovieDataSource(endpoint)

    @Provides
    @Singleton
    fun providePopularDataSource(
        endpoint: ApiService
    ) : PopularMovieDataSource = PopularMovieDataSource(endpoint)
}