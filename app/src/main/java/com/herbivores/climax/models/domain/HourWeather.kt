package com.herbivores.climax.models.domain

data class HourWeather(
    val time: String, // TODO: Change this to LocalTime
    val iconUrl: String,
    val type: String,
    val temperature: Temperature,
    val feelsLike: Temperature,
)