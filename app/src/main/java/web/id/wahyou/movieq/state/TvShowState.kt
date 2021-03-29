package web.id.wahyou.movieq.state

import web.id.wahyou.movieq.data.model.tvshow.ResponseTvShow

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

sealed class TvShowState {
    object Loading : TvShowState()
    data class Result(val data : ResponseTvShow) : TvShowState()
    data class Error(val error : Throwable) : TvShowState()
}