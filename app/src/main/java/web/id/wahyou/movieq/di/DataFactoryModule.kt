package web.id.wahyou.movieq.di
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import web.id.wahyou.movieq.data.factory.Factory
import web.id.wahyou.movieq.data.factory.movie.PopularMovieDataFactory
import web.id.wahyou.movieq.data.factory.movie.SearchMovieDataFactory
import web.id.wahyou.movieq.data.factory.movie.TopRatedMovieDataFactory
import web.id.wahyou.movieq.data.factory.movie.UpcomingMovieDataFactory
import web.id.wahyou.movieq.data.factory.tvshow.AiringTvShowDataFactory
import web.id.wahyou.movieq.data.factory.tvshow.PopularTvShowDataFactory
import web.id.wahyou.movieq.data.factory.tvshow.SearchTvDataFactory
import web.id.wahyou.movieq.data.factory.tvshow.TopRatedTvShowDataFactory
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
class DataFactoryModule {

    @Provides
    @Singleton
    fun provideFactory(
        upcomingMovieDataFactory: UpcomingMovieDataFactory,
        topRatedMovieDataFactory: TopRatedMovieDataFactory,
        popularMovieDataFactory: PopularMovieDataFactory,
        searchMovieDataFactory: SearchMovieDataFactory,
        airingTvShowDataFactory: AiringTvShowDataFactory,
        topRatedTvShowDataFactory: TopRatedTvShowDataFactory,
        popularTvShowDataFactory: PopularTvShowDataFactory,
        searchTvDataFactory: SearchTvDataFactory,
    ) : Factory = Factory(
        upcomingMovieDataFactory,
        topRatedMovieDataFactory,
        popularMovieDataFactory,
        searchMovieDataFactory,
        airingTvShowDataFactory,
        topRatedTvShowDataFactory,
        popularTvShowDataFactory,
        searchTvDataFactory
    )

    @Provides
    @Singleton
    fun provideUpcomingMovieFactory(
            upcomingMovieDataSource: UpcomingMovieDataSource
    ) : UpcomingMovieDataFactory = UpcomingMovieDataFactory(upcomingMovieDataSource)

    @Provides
    @Singleton
    fun provideTopRatedMovieFactory(
            topRatedMovieDataSource: TopRatedMovieDataSource
    ) : TopRatedMovieDataFactory = TopRatedMovieDataFactory(topRatedMovieDataSource)

    @Provides
    @Singleton
    fun providePopularMovieFactory(
            popularMovieDataSource: PopularMovieDataSource
    ) : PopularMovieDataFactory = PopularMovieDataFactory(popularMovieDataSource)

    @Provides
    @Singleton
    fun provideSearchMovieFactory(
            searchMovieDataSource: SearchMovieDataSource
    ) : SearchMovieDataFactory = SearchMovieDataFactory(searchMovieDataSource)

    @Provides
    @Singleton
    fun provideAiringTvDataFactory(
            airingTvDataSource: AiringTvDataSource
    ) : AiringTvShowDataFactory = AiringTvShowDataFactory(airingTvDataSource)

    @Provides
    @Singleton
    fun provideTopRatedTvDataFactory(
            topRatedTvDataSource: TopRatedTvDataSource
    ) : TopRatedTvShowDataFactory = TopRatedTvShowDataFactory(topRatedTvDataSource)

    @Provides
    @Singleton
    fun providePopularTvDataFactory(
            popularTvDataSource: PopularTvDataSource
    ) : PopularTvShowDataFactory = PopularTvShowDataFactory(popularTvDataSource)

    @Provides
    @Singleton
    fun provideSearchTvFactory(
            searchTvDataSource: SearchTvDataSource
    ) : SearchTvDataFactory = SearchTvDataFactory(searchTvDataSource)


}