package web.id.wahyou.movieq.data.mapper

import web.id.wahyou.movieq.data.database.model.MovieEntity
import web.id.wahyou.movieq.data.model.movie.DataMovie

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

object MovieMapper {
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
