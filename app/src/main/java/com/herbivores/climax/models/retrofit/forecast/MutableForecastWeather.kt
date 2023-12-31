package com.herbivores.climax.models.retrofit.forecast

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.herbivores.climax.constants.WeatherApi
import com.herbivores.climax.models.domain.celsius
import com.herbivores.climax.models.domain.forecast.DayWeather
import com.herbivores.climax.models.domain.forecast.ForecastWeather
import java.time.Instant
import java.time.ZoneId
import kotlin.math.roundToInt

@Keep
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
                humidity = hourWeatherList.map { it.main?.humidity ?: 0 }.average().roundToInt(),
                maxTemperature = (hourWeatherList.maxOfOrNull { it.main?.tempMax ?: 0.0 } ?:0.0).celsius,
                maxFeelsLike = (hourWeatherList.maxOfOrNull { it.main?.feelsLike ?: 0.0 } ?: 0.0).celsius,
                wind = hourWeatherList.map { it.wind?.toWind() }.maxByOrNull { it?.speedMetersPerSecond ?: 0.0 }?: Wind().toWind(),
                sunrise = Instant.ofEpochSecond(city?.sunrise?.toLong()?:0)
                    .atZone(ZoneId.systemDefault())
                    .toLocalTime(),
                sunset = Instant.ofEpochSecond(city?.sunset?.toLong()?:0)
                    .atZone(ZoneId.systemDefault())
                    .toLocalTime(),
                hourlyWeather = hourWeatherList.map { it.toHourWeather() },
                iconUrl = WeatherApi.getIconUrl(
                    hourWeatherList
                        .flatMap { it.weather }
                        .groupBy { it.icon }
                        .maxByOrNull { it.value.size }
                        ?.key
                        ?: ""
                )
            )
        }
    )
}