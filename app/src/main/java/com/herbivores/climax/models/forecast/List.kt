package com.herbivores.climax.models.forecast

data class List(
    val iconName: String,
    val type: String,
    val day: String,
    val time: String,
    val temperature: String,
    val feelsLike: String,
)
