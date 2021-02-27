package web.id.wahyou.movieq.utils

import web.id.wahyou.movieq.data.database.model.MovieEntity
import web.id.wahyou.movieq.data.model.movie.DataMovie

object Mapper {
    fun mapEntityToResponse(data: MovieEntity) =
            DataMovie(
                    data.backdrop_path,
                    data.id, data.overview,
                    data.poster_path,
                    data.title,
                    data.release_date,
                    data.vote_average,
                    data.popularity,
                    genreIds = null,
                    movieType = ""
            )

    fun mapResponseToEntity(data: DataMovie) =
            MovieEntity(
                    data.backdrop_path,
                    data.id, data.overview,
                    data.poster_path,
                    data.title,
                    data.release_date,
                    data.vote_average,
                    data.popularity,
                    data.genreIds?.get(0).toString()
            )
}
