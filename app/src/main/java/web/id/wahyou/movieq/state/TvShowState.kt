package web.id.wahyou.movieq.state

import web.id.wahyou.movieq.data.model.tvshow.ResponseTvShow

sealed class TvShowState {
    object Loading : TvShowState()
    data class Result(val data : ResponseTvShow) : TvShowState()
    data class Error(val error : Throwable) : TvShowState()
}