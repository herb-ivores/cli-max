package com.herbivores.climax.models.domain

data class DayWeather(
    val day: String,
    val hourlyWeather: List<HourWeather>,
)