package com.herbivores.climax.models.domain.forecast

data class ForecastWeather(
    val location: String,
    val dailyWeather: List<DayWeather>,
)