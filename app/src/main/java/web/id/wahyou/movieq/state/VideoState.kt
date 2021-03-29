package web.id.wahyou.movieq.state

import web.id.wahyou.movieq.data.model.videos.ResponseVideo

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

sealed class VideoState {
    object Loading : VideoState()
    data class Result(val data : ResponseVideo) : VideoState()
    data class Error(val error : Throwable) : VideoState()
}