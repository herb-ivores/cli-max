package com.herbivores.climax.models.current

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.herbivores.climax.constants.WeatherApi
import com.herbivores.climax.models.domain.CurrentWeather
import com.herbivores.climax.models.domain.Direction
import com.herbivores.climax.models.domain.celsius
import com.herbivores.climax.models.domain.meters
import java.time.Instant
import java.time.ZoneId

@Keep
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
    @SerializedName("precipitation")
    var precipitation: Precipitation? = null,
) {
    fun toCurrentWeather() = CurrentWeather(
        location = name ?: "",
        dateTime = Instant.ofEpochSecond(dt ?: 0)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime(),
        iconUrl = WeatherApi.getIconUrl(weather.firstOrNull()?.icon ?: ""),
        type = weather.firstOrNull()?.main ?: "",
        temperature = (main?.temp ?: 0.0).celsius,
        feelsLike = (main?.feelsLike ?: 0.0).celsius,
        wind = com.herbivores.climax.models.domain.Wind(
            speedMetersPerSecond = wind?.speed ?: 0.0,
            direction = Direction(degrees = wind?.deg ?: 0),
        ),
        humidityPercent = main?.humidity ?: 0,
        pressureMillibars = main?.pressure ?: 0,
        visibility = (visibility ?: 0).meters,
        precipitationMillimeters = precipitation?.value ?: 0.0,
        sunrise = Instant.ofEpochSecond(sys?.sunrise ?: 0)
            .atZone(ZoneId.systemDefault())
            .toLocalTime(),
        sunset = Instant.ofEpochSecond(sys?.sunset ?: 0)
            .atZone(ZoneId.systemDefault())
            .toLocalTime(),
    )
}