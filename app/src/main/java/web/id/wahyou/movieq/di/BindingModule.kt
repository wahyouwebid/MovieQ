package web.id.wahyou.movieq.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import web.id.wahyou.movieq.data.repository.DataRepository
import web.id.wahyou.movieq.data.repository.Repository

@Module
@InstallIn(ApplicationComponent::class)
abstract class BindingModule {
    @Binds
    abstract fun bindRepository(
        dataRepository: DataRepository
    ) : Repository
}