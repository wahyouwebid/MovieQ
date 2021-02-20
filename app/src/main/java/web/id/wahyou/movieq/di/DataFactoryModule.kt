package web.id.wahyou.movieq.di
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import web.id.wahyou.movieq.data.factory.Factory
import web.id.wahyou.movieq.data.factory.MovieDataFactory
import web.id.wahyou.movieq.data.source.movie.MovieDataSource
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataFactoryModule {

    @Provides
    @Singleton
    fun provideFactory(
        movieDataFactory: MovieDataFactory
    ) : Factory = Factory(
        movieDataFactory
    )

    @Provides
    @Singleton
    fun provideMovieFactory(
        movieDataSource: MovieDataSource
    ) : MovieDataFactory = MovieDataFactory(movieDataSource)
}