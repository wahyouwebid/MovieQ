package web.id.wahyou.movieq.di

import androidx.paging.PagedList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import web.id.wahyou.movieq.data.factory.Factory
import web.id.wahyou.movieq.data.network.ApiService
import web.id.wahyou.movieq.data.repository.DataRepository
import web.id.wahyou.movieq.data.repository.remote.RemoteRepository
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRemoteRepository(
        apiService: ApiService,
        config : PagedList.Config,
        factory : Factory
    ) : RemoteRepository = RemoteRepository(
            apiService,
            config,
            factory
        )

    @Singleton
    @Provides
    fun provideDataRepository(
            remoteRepository: RemoteRepository
    ) : DataRepository = DataRepository(remoteRepository)
}