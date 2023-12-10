package com.herbivores.climax.models.forecast

import com.google.gson.annotations.SerializedName
import com.herbivores.climax.models.domain.DayWeather
import com.herbivores.climax.models.domain.ForecastWeather
import com.herbivores.climax.models.domain.Temperature
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
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
            Instant.ofEpochSecond(it.dt?.toLong() ?: 0).atZone(ZoneId.systemDefault()).toLocalDate()
        }.map { (day, hourWeatherList) ->
            DayWeather(
                date = day,
                humidity = hourWeatherList.map { it.main?.humidity?.toDouble() ?: 0.0 }.maxOrNull() ?: 0.0,
                maxTemperature = Temperature(hourWeatherList.map { it.main?.tempMax?: 0.0}.maxOrNull()?:0.0),
                maxFeelsLike = Temperature(hourWeatherList.map { it.main?.feelsLike ?:0.0 }.maxOrNull() ?: 0.0),
                wind = Wind().toWind(),
                sunrise = Instant.ofEpochSecond(City().sunrise?.toLong()?:0)
                    .atZone(ZoneId.systemDefault())
                    .toLocalTime(),
                sunset = Instant.ofEpochSecond(City().sunset?.toLong()?:0)
                    .atZone(ZoneId.systemDefault())
                    .toLocalTime(),
                hourlyWeather = hourWeatherList.map { it.toHourWeather() },
            )
        }
    )
}