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
import web.id.wahyou.movieq.data.source.tv.AiringTvDataSource
import web.id.wahyou.movieq.data.source.tv.PopularTvDataSource
import web.id.wahyou.movieq.data.source.tv.SearchTvDataSource
import web.id.wahyou.movieq.data.source.tv.TopRatedTvDataSource
import javax.inject.Singleton

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

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
    fun providePopularMovieDataSource(
            apiservice: ApiService
    ) : PopularMovieDataSource = PopularMovieDataSource(apiservice)

    @Provides
    @Singleton
    fun provideSearchMovieDataSource(
            apiservice: ApiService
    ) : SearchMovieDataSource = SearchMovieDataSource(apiservice)

    @Provides
    @Singleton
    fun provideAiringTvDataSource(
            apiservice: ApiService
    ) : AiringTvDataSource = AiringTvDataSource(apiservice)

    @Provides
    @Singleton
    fun provideTopRatedTvDataSource(
            apiservice: ApiService
    ) : TopRatedTvDataSource = TopRatedTvDataSource(apiservice)

    @Provides
    @Singleton
    fun providePopularTvDataSource(
            apiservice: ApiService
    ) : PopularTvDataSource = PopularTvDataSource(apiservice)

    @Provides
    @Singleton
    fun provideSearchTvDataSource(
            apiservice: ApiService
    ) : SearchTvDataSource = SearchTvDataSource(apiservice)
}