package com.herbivores.climax.constants

object WeatherApi {
    const val BASE_URL = "https://api.openweathermap.org"
    const val KEY = ApiKeys.WEATHER_API
    const val CURRENT_WEATHER = "/data/2.5/weather"
    const val FORECAST_WEATHER = "/data/2.5/forecast"
    const val ICON_BASE_URL = "https://openweathermap.org/img/wn/"
    const val NEW_ICON_BASE_URL = "https://raw.githubusercontent.com/herb-ivores/weather-icons/main/icons/"

//    fun getIconUrl(iconName: String) = "$ICON_BASE_URL$iconName@2x.png"
    fun getIconUrl(iconName: String) = "$NEW_ICON_BASE_URL$iconName.png"
}