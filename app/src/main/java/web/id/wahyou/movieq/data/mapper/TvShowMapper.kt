package web.id.wahyou.movieq.data.mapper

import web.id.wahyou.movieq.data.database.model.TvShowEntity
import web.id.wahyou.movieq.data.model.tvshow.DataTvShow

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

object TvShowMapper {
    fun mapEntityToResponse(data: TvShowEntity) =
            DataTvShow(
                    data.backdrop_path,
                    data.id, data.overview,
                    data.poster_path,
                    data.name,
                    data.first_air_date,
                    data.vote_average,
                    data.popularity
            )

    fun mapResponseToEntity(data: DataTvShow) =
            TvShowEntity(
                    data.backdrop_path,
                    data.id, data.overview,
                    data.poster_path,
                    data.name,
                    data.first_air_date,
                    data.vote_average,
                    data.popularity
            )
}
