package com.herbivores.climax.models.forecast

import com.google.gson.annotations.SerializedName
import com.herbivores.climax.constants.WeatherApi
import com.herbivores.climax.models.domain.HourWeather
import com.herbivores.climax.models.domain.celsius
import java.time.Instant
import java.time.ZoneId

data class List (
    @SerializedName("dt")
    var dt: Int? = null,
    @SerializedName("main")
    var main: Main? = Main(),
    @SerializedName("weather")
    var weather: ArrayList<Weather> = arrayListOf(),
    @SerializedName("clouds")
    var clouds: Clouds? = Clouds(),
    @SerializedName("wind")
    var wind: Wind? = Wind(),
    @SerializedName("visibility")
    var visibility: Int? = null,
    @SerializedName("pop")
    var pop: Double? = null,
    @SerializedName("rain")
    var rain: Rain? = Rain(),
    @SerializedName("sys")
    var sys: Sys? = Sys(),
    @SerializedName("dt_txt")
    var dtTxt: String? = null
){
    fun toHourWeather() = HourWeather(
        iconUrl = WeatherApi.getIconUrl(weather.firstOrNull()?.icon ?: ""),
        type = weather.firstOrNull()?.main.orEmpty(),
        temperature = (main?.temp ?: 0.0).celsius,
        feelsLike = (main?.feelsLike ?: 0.0).celsius,
        time = Instant.ofEpochSecond(dt?.toLong()?:0)
            .atZone(ZoneId.systemDefault())
            .toLocalTime(),
    )

}
