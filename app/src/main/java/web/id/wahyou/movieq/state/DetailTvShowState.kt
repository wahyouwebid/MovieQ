package web.id.wahyou.movieq.state

import web.id.wahyou.movieq.data.model.detailtv.ResponseDetailTv

sealed class DetailTvShowState {
    object Loading : DetailTvShowState()
    data class Result(val data : ResponseDetailTv) : DetailTvShowState()
    data class Error(val error : Throwable) : DetailTvShowState()
}