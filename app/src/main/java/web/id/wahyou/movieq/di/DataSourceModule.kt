package web.id.wahyou.movieq.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import web.id.wahyou.movieq.data.network.ApiService
import web.id.wahyou.movieq.data.source.movie.PopularMovieDataSource
import web.id.wahyou.movieq.data.source.movie.SearchMovieDataSource
import web.id.wahyou.movieq.data.source.movie.TopRatedMovieDataSource
import web.id.wahyou.movieq.data.source.movie.UpcomingMovieDataSource
import web.id.wahyou.movieq.data.source.tv.SearchTvDataSource
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataSourceModule {
    @Provides
    @Singleton
    fun provideUpcomingMovieDataSource(
        apiservice: ApiService
    ) : UpcomingMovieDataSource = UpcomingMovieDataSource(apiservice)

    @Provides
    @Singleton
    fun provideTopRatedDataSource(
            apiservice: ApiService
    ) : TopRatedMovieDataSource = TopRatedMovieDataSource(apiservice)

    @Provides
    @Singleton
    fun providePopularDataSource(
            apiservice: ApiService
    ) : PopularMovieDataSource = PopularMovieDataSource(apiservice)

    @Provides
    @Singleton
    fun provideSearchMovieDataSource(
            apiservice: ApiService
    ) : SearchMovieDataSource = SearchMovieDataSource(apiservice)

    @Provides
    @Singleton
    fun provideSearchTvDataSource(
            apiservice: ApiService
    ) : SearchTvDataSource = SearchTvDataSource(apiservice)
}