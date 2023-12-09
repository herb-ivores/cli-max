package com.herbivores.climax.models.forecast

import com.google.gson.annotations.SerializedName
import com.herbivores.climax.models.domain.DayWeather
import com.herbivores.climax.models.domain.ForecastWeather
import java.text.SimpleDateFormat
import java.util.Locale

data class MutableForecastWeather(
    @SerializedName("cod")
    var cod : String? = null,
    @SerializedName("message" )
    var message : Int? = null,
    @SerializedName("cnt")
    var cnt  : Int? = null,
    @SerializedName("list")
    var list : ArrayList<List> = arrayListOf(),
    @SerializedName("city")
    var city : City?  = City()
){
    fun toForecastWeather() = ForecastWeather(

        location = city?.name.orEmpty(),
        dailyWeather = list.groupBy {
            SimpleDateFormat("EEEE", Locale.ENGLISH).format((it.dt ?: 0) * 1000)
        }.map { (day, hourWeatherList) ->
            DayWeather(
                day = day,
                hourlyWeather = hourWeatherList.map { it.toHourWeather() }
            )
        }
    )
}