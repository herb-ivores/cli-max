package com.herbivores.climax.models.domain

import com.herbivores.climax.models.Temperature

data class HourWeather(
    val time: String,
    val iconUrl: String,
    val type: String,
    val temperature: Temperature,
    val feelsLike: Temperature,
)