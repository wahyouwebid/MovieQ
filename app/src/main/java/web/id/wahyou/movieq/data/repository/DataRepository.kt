package web.id.wahyou.movieq.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import web.id.wahyou.movieq.data.database.RoomDb
import web.id.wahyou.movieq.data.database.model.MovieEntity
import web.id.wahyou.movieq.data.database.model.TvShowEntity
import web.id.wahyou.movieq.data.model.movie.DataMovie
import web.id.wahyou.movieq.data.model.tvshow.DataTvShow
import web.id.wahyou.movieq.data.repository.local.LocalRepository
import web.id.wahyou.movieq.data.repository.remote.RemoteRepository
import web.id.wahyou.movieq.state.DetailMovieState
import web.id.wahyou.movieq.state.DetailTvShowState
import web.id.wahyou.movieq.state.MovieState
import web.id.wahyou.movieq.state.TvShowState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) : Repository {
    override fun getNowPlayingMovie(callback: MutableLiveData<MovieState>){
        remoteRepository.getNowPlayingMovie(callback)
    }

    override fun getUpcomingMovie(callback: MutableLiveData<MovieState>){
        remoteRepository.getUpcomingMovie(callback)
    }

    override fun getPopularMovie(callback: MutableLiveData<MovieState>){
        remoteRepository.getPopularMovie(callback)
    }

    override fun getTopRatedMovie(callback: MutableLiveData<MovieState>){
        remoteRepository.getTopRatedMovie(callback)
    }

    override fun getDetailMovie(movieId: Int, callback: MutableLiveData<DetailMovieState>){
        remoteRepository.getDetailMovie(movieId, callback)
    }

    override fun getAllUpcomingMovie(
            callback: MutableLiveData<MovieState>,
            data: MutableLiveData<PagedList<DataMovie>>
    ){
        remoteRepository.getAllUpcomingMovie(callback, data)
    }

    override fun getAllTopRatedMovie(
            callback: MutableLiveData<MovieState>,
            data: MutableLiveData<PagedList<DataMovie>>
    ){
        remoteRepository.getAllTopRatedMovie(callback, data)
    }

    override fun getAllPopularMovie(
            callback: MutableLiveData<MovieState>,
            data: MutableLiveData<PagedList<DataMovie>>
    ){
        remoteRepository.getAllPopularMovie(callback, data)
    }

    override fun searchMovie(
            query: String,
            callback: MutableLiveData<MovieState>,
            data: MutableLiveData<PagedList<DataMovie>>
    ) {
        remoteRepository.searchMovie(query, callback, data)
    }

    override fun getAiringTodayTvShow(
            callback: MutableLiveData<TvShowState>
    ){
        remoteRepository.getAiringTodayTvShow(callback)
    }

    override fun getTopRatedTvShow(
            callback: MutableLiveData<TvShowState>
    ){
        remoteRepository.getTopRatedTvShow(callback)
    }

    override fun getPopularTvShow(
            callback: MutableLiveData<TvShowState>
    ){
        remoteRepository.getPopularTvShow(callback)
    }

    override fun getDetailTvShow(
            tvId: Int, callback: MutableLiveData<DetailTvShowState>
    ){
        remoteRepository.getDetailTvShow(tvId, callback)
    }

    override fun getAllAiringTodayTvShow(
            callback: MutableLiveData<TvShowState>,
            data: MutableLiveData<PagedList<DataTvShow>>
    ){
        remoteRepository.getAllAiringTodayTvShow(callback, data)
    }

    override fun getAllTopRatedTvShow(
            callback: MutableLiveData<TvShowState>,
            data: MutableLiveData<PagedList<DataTvShow>>
    ){
        remoteRepository.getAllTopRatedTvShow(callback, data)
    }

    override fun getAllPopularTvShow(
            callback: MutableLiveData<TvShowState>,
            data: MutableLiveData<PagedList<DataTvShow>>
    ){
        remoteRepository.getAllPopularTvShow(callback, data)
    }

    override fun searchTvShow(
            query: String,
            callback: MutableLiveData<TvShowState>,
            data: MutableLiveData<PagedList<DataTvShow>>
    ){
        remoteRepository.searchTvShow(query, callback, data)
    }

    override fun getFavoriteMovie(data: MutableLiveData<PagedList<MovieEntity>>) {
        localRepository.getFavoriteMovie(data)
    }

    override fun searchFavoriteMovie(query: String, data: MutableLiveData<PagedList<MovieEntity>>) {
        localRepository.searchFavoriteMovie(query, data)
    }

    override fun getFavoriteTvShow(data: MutableLiveData<PagedList<TvShowEntity>>) {
        localRepository.getFavoriteTvShow(data)
    }

    override fun searchFavoriteTvShow(query: String, data: MutableLiveData<PagedList<TvShowEntity>>) {
        localRepository.searchFavoriteTvShow(query, data)
    }

    override fun addDataMovie(data: MovieEntity) {
        localRepository.addDataMovie(data)
    }

    override fun checkDataMovie(data: MovieEntity): List<MovieEntity> {
        return localRepository.checkDataMovie(data)
    }

    override fun deleteDataMovie(data: MovieEntity) {
        localRepository.deleteDataMovie(data)
    }

    override fun addDataTvShow(data: TvShowEntity) {
        localRepository.addDataTvShow(data)
    }

    override fun checkDataTvShow(data: TvShowEntity): List<TvShowEntity> {
        return localRepository.checkDataTvShow(data)
    }

    override fun deleteDataTvShow(data: TvShowEntity) {
        localRepository.deleteDataTvShow(data)
    }

    override fun getDisposible(): CompositeDisposable {
        return remoteRepository.getDisposible()
    }

    override fun getDatabase(): RoomDb {
        return localRepository.getDatabase()
    }
}