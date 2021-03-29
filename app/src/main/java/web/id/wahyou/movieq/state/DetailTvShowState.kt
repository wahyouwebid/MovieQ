package web.id.wahyou.movieq.state

import web.id.wahyou.movieq.data.model.detailtv.ResponseDetailTv

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

sealed class DetailTvShowState {
    object Loading : DetailTvShowState()
    data class Result(val data : ResponseDetailTv) : DetailTvShowState()
    data class Error(val error : Throwable) : DetailTvShowState()
}