package com.herbivores.climax.models.domain

data class ForecastWeather(
    val location: String,
    val forecast: List<DayWeather>,
)