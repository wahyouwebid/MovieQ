package web.id.wahyou.movieq.di

import androidx.paging.PagedList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class UtilsModule {

    @Provides
    @Singleton
    fun provideConfig() : PagedList.Config = PagedList.Config.Builder()
        .setPageSize(1)
        .setInitialLoadSizeHint(2)
        .setPrefetchDistance(1)
        .setEnablePlaceholders(false)
        .build()
}