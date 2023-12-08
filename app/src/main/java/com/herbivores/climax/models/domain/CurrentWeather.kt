package com.herbivores.climax.models.domain

import com.herbivores.climax.models.Temperature

data class CurrentWeather(
    val location: String,
    val iconUrl: String,
    val type: String,
    val day: String,
    val time: String,
    val temperature: Temperature,
    val feelsLike: Temperature,
)
