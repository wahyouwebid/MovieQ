package web.id.wahyou.movieq.state

import web.id.wahyou.movieq.data.model.detailmovie.ResponseDetailMovie

sealed class DetailMovieState {
    object Loading : DetailMovieState()
    data class Result(val data : ResponseDetailMovie) : DetailMovieState()
    data class Error(val error : Throwable) : DetailMovieState()
}