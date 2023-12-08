package com.herbivores.climax.models.current

data class CurrentWeather(
    val location: String,
    val iconName: String,
    val type: String,
    val day: String,
    val time: String,
    val temperature: String,
    val feelsLike: String,
)
