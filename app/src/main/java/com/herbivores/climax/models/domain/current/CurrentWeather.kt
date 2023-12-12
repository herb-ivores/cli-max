package com.herbivores.climax.models.domain.current

import com.herbivores.climax.models.domain.Distance
import com.herbivores.climax.models.domain.Temperature
import com.herbivores.climax.models.domain.Wind
import java.time.LocalDateTime
import java.time.LocalTime

data class CurrentWeather(
    val location: String,
    val dateTime: LocalDateTime,
    val iconUrl: String,
    val type: String,
    val temperature: Temperature,
    val feelsLike: Temperature,
    val wind: Wind,
    val humidityPercent: Int,
    val pressureMillibars: Int,
    val visibility: Distance,
    val precipitationMillimeters: Double,
    val sunrise: LocalTime,
    val sunset: LocalTime,
)