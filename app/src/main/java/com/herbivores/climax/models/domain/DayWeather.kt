package com.herbivores.climax.models.domain

enum class Day(val friendlyName: String) {
    Monday("Monday"),
    Tuesday("Tuesday"),
    Wednesday("Wednesday"),
    Thursday("Thursday"),
    Friday("Friday"),
    Saturday("Saturday"),
    Sunday("Sunday"),
}

data class DayWeather(
    val day: String, // TODO: Change this to the enum Day
    // TODO: Add these:
//    val maxTemperature: Temperature,
//    val maxFeelsLike: Temperature,
//    val wind: Wind,
//    val humidity: Double,
//    val sunrise: LocalTime,
//    val sunset: LocalTime,
    val hourlyWeather: List<HourWeather>,
)