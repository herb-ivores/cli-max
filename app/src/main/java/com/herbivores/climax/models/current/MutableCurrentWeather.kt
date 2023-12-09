package com.herbivores.climax.models.current

import com.google.gson.annotations.SerializedName
import com.herbivores.climax.constants.WeatherApi
import com.herbivores.climax.models.celsius
import com.herbivores.climax.models.domain.CurrentWeather
import java.text.SimpleDateFormat
import java.util.Locale

// TODO: Please complete these and revert their names.
// Check all the fields from https://openweathermap.org/current and add them all here.
data class MutableCurrentWeather(
    @SerializedName("coord")
    var coord: Coord? = Coord(),
    @SerializedName("weather")
    var weather: List<Weather> = listOf(),
    @SerializedName("base")
    var base: String? = null,
    @SerializedName("main")
    var main: Main? = Main(),
    @SerializedName("visibility")
    var visibility: Int? = null,
    @SerializedName("wind")
    var wind: Wind? = Wind(),
    @SerializedName("clouds")
    var clouds: Clouds? = Clouds(),
    @SerializedName("dt")
    var dt: Long? = null,
    @SerializedName("sys")
    var sys: Sys? = Sys(),
    @SerializedName("timezone")
    var timezone: Int? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("cod")
    var cod: Int? = null,
) {
    fun toCurrentWeather() = CurrentWeather(
        location = name ?: "",
        iconUrl = WeatherApi.getIconUrl(weather.firstOrNull()?.icon ?: ""),
        type = weather.firstOrNull()?.main ?: "",
        day = SimpleDateFormat("hh:mm aa", Locale.ENGLISH).format((dt ?: 0) * 1000),
        time = SimpleDateFormat("EEEE", Locale.ENGLISH).format((dt ?: 0) * 1000),
        temperature = (main?.temp ?: 0.0).celsius,
        feelsLike = (main?.feelsLike ?: 0.0).celsius,
    )
}