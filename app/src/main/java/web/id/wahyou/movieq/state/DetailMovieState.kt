package web.id.wahyou.movieq.state

import web.id.wahyou.movieq.data.model.detailmovie.ResponseDetailMovie

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

sealed class DetailMovieState {
    object Loading : DetailMovieState()
    data class Result(val data : ResponseDetailMovie) : DetailMovieState()
    data class Error(val error : Throwable) : DetailMovieState()
}