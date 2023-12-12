package com.herbivores.climax.models.domain.forecast

import com.herbivores.climax.models.domain.Temperature
import com.herbivores.climax.models.domain.Wind
import java.time.LocalDate
import java.time.LocalTime

data class DayWeather(
    val date: LocalDate,
    val iconUrl: String,
    val maxTemperature: Temperature,
    val maxFeelsLike: Temperature,
    val wind: Wind,
    val humidity: Int,
    val sunrise: LocalTime,
    val sunset: LocalTime,
    val hourlyWeather: List<HourWeather>,
)