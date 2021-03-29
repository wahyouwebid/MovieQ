package web.id.wahyou.movieq.di

import android.content.Context
import androidx.paging.PagedList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import web.id.wahyou.movieq.data.database.RoomDb
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
class UtilsModule {

    @Provides
    @Singleton
    fun provideConfig() : PagedList.Config = PagedList.Config.Builder()
        .setPageSize(1)
        .setInitialLoadSizeHint(2)
        .setPrefetchDistance(1)
        .setEnablePlaceholders(false)
        .build()

    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext context: Context
    ) : RoomDb = RoomDb(context)
}