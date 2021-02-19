package web.id.wahyou.movieq.state

import web.id.wahyou.movieq.data.model.movie.ResponseMovie

sealed class MovieState {
    object Loading : MovieState()
    data class Result(val data : ResponseMovie) : MovieState()
    data class Error(val error : Throwable) : MovieState()
}