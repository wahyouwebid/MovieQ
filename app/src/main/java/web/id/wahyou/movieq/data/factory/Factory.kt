package web.id.wahyou.movieq.data.factory

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class Factory  @Inject constructor(
    val movieDataFactory: MovieDataFactory
)