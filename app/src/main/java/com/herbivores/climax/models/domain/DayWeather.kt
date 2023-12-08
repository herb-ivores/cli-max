package com.herbivores.climax.models.domain

import com.herbivores.climax.models.Temperature

data class DayWeather(
    val day: String,
    val iconUrl: String,
    val type: String,
    val temperature: Temperature,
    val feelsLike: Temperature,
)