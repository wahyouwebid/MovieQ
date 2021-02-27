package web.id.wahyou.movieq.state

import web.id.wahyou.movieq.data.model.videos.ResponseVideo

sealed class VideoState {
    object Loading : VideoState()
    data class Result(val data : ResponseVideo) : VideoState()
    data class Error(val error : Throwable) : VideoState()
}