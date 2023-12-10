package com.herbivores.climax.models.domain

import java.time.LocalTime

data class HourWeather(
    val time: LocalTime,
    val iconUrl: String,
    val type: String,
    val temperature: Temperature,
    val feelsLike: Temperature,
)