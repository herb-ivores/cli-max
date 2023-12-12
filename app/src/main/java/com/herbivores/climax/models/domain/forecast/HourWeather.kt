package com.herbivores.climax.models.domain.forecast

import com.herbivores.climax.models.domain.Temperature
import java.time.LocalTime

data class HourWeather(
    val time: LocalTime,
    val iconUrl: String,
    val type: String,
    val temperature: Temperature,
    val feelsLike: Temperature,
)