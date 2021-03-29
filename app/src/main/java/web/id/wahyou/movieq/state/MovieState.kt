package web.id.wahyou.movieq.state

import web.id.wahyou.movieq.data.model.movie.ResponseMovie

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

sealed class MovieState {
    object Loading : MovieState()
    data class Result(val data : ResponseMovie) : MovieState()
    data class Error(val error : Throwable) : MovieState()
}