package com.herbivores.climax.models.forecast

import com.google.gson.annotations.SerializedName
import com.herbivores.climax.constants.WeatherApi
import com.herbivores.climax.models.domain.DayWeather
import com.herbivores.climax.models.domain.ForecastWeather
import com.herbivores.climax.models.domain.Temperature
import java.time.Instant
import java.time.ZoneId

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
                humidity = hourWeatherList.map { it.main?.humidity?.toDouble() ?: 0.0 }.average(),
                maxTemperature = Temperature(hourWeatherList.map { it.main?.tempMax?: 0.0}.maxOrNull()?:0.0),
                maxFeelsLike = Temperature(hourWeatherList.map { it.main?.feelsLike ?:0.0 }.maxOrNull() ?: 0.0),
                wind = hourWeatherList.map { it.wind?.toWind() }.maxByOrNull { it?.speedMetersPerSecond ?: 0.0 }?:Wind().toWind(),
                sunrise = Instant.ofEpochSecond(City().sunrise?.toLong()?:0)
                    .atZone(ZoneId.systemDefault())
                    .toLocalTime(),
                sunset = Instant.ofEpochSecond(City().sunset?.toLong()?:0)
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