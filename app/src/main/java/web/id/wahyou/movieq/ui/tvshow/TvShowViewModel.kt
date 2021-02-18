package web.id.wahyou.movieq.ui.tvshow

import androidx.lifecycle.ViewModel
import web.id.wahyou.movieq.model.DataMovie
import web.id.wahyou.movieq.model.DataTvShow
import web.id.wahyou.movieq.utils.DataDummy

class TvShowViewModel : ViewModel() {

    fun getData(): List<DataTvShow> {
        val data: MutableList<DataTvShow> = ArrayList()
        val json = DataDummy.movieData

        for (i in json.indices) {
            val values = json[i]
            data.add(
                DataTvShow(
                    values[0],
                    values[1],
                    values[2],
                    values[3],
                    values[4],
                    values[5],
                    values[6]
                )
            )
        }

        return data
    }

}