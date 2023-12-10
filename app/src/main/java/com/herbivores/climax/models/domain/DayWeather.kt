package com.herbivores.climax.models.domain

import java.time.LocalDate
import java.time.LocalTime

data class DayWeather(
    val date: LocalDate, // Replace day with this (date). We'll just format the day in the UI.
    val maxTemperature: Temperature,
    val maxFeelsLike: Temperature,
    val wind: Wind,
    val humidity: Double,
    val sunrise: LocalTime,
    val sunset: LocalTime,
    val hourlyWeather: List<HourWeather>,
)