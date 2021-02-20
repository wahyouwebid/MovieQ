package web.id.wahyou.movieq.di
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import web.id.wahyou.movieq.data.factory.Factory
import web.id.wahyou.movieq.data.factory.movie.PopularMovieDataFactory
import web.id.wahyou.movieq.data.factory.movie.TopRatedMovieDataFactory
import web.id.wahyou.movieq.data.factory.movie.UpcomingMovieDataFactory
import web.id.wahyou.movieq.data.source.movie.PopularMovieDataSource
import web.id.wahyou.movieq.data.source.movie.TopRatedMovieDataSource
import web.id.wahyou.movieq.data.source.movie.UpcomingMovieDataSource
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataFactoryModule {

    @Provides
    @Singleton
    fun provideFactory(
        upcomingMovieDataFactory: UpcomingMovieDataFactory,
        topRatedMovieDataFactory: TopRatedMovieDataFactory,
        popularMovieDataFactory: PopularMovieDataFactory
    ) : Factory = Factory(
        upcomingMovieDataFactory,
        topRatedMovieDataFactory,
        popularMovieDataFactory
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


}