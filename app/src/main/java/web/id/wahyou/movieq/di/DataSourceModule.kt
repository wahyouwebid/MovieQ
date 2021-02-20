package web.id.wahyou.movieq.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import web.id.wahyou.movieq.data.network.ApiService
import web.id.wahyou.movieq.data.source.movie.MovieDataSource
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataSourceModule {
    @Provides
    @Singleton
    fun provideMovieDataSource(
        endpoint: ApiService
    ) : MovieDataSource = MovieDataSource(endpoint)
}