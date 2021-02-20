package web.id.wahyou.movieq.utils

import android.os.Handler
import android.os.Looper
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    @Throws(ParseException::class)
    fun dateFormat(date: String, input : String, output : String) : String{
        var format = SimpleDateFormat(input, Locale.getDefault())

        val newDate: Date? = format.parse(date)

        format = SimpleDateFormat(output, Locale.getDefault())

        return format.format(newDate!!)
    }

    fun nomalizeRating(oldValue: Float): Float{
        return ((oldValue-0)/10-0)*((5-0)+0)
    }

    fun delay() {
        EspressoIdlingResource.increment()
        Handler(Looper.getMainLooper()).postDelayed({
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                EspressoIdlingResource.decrement()
            }
        }, 3000)
    }
}