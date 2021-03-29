package web.id.wahyou.movieq.data.database.dao

import androidx.paging.DataSource
import androidx.room.*
import web.id.wahyou.movieq.data.database.model.MovieEntity

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(data : MovieEntity) : Long

    @Query("SELECT * from movie where id = :id")
    fun getDataById(id : Int) : List<MovieEntity>

    @Query("SELECT * from movie")
    fun getData() : DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * from movie")
    fun getDataList() : List<MovieEntity>

    @Query("SELECT * From movie where title like :query")
    fun searchData(query : String) : DataSource.Factory<Int, MovieEntity>

    @Delete
    fun delete(data : MovieEntity)
}