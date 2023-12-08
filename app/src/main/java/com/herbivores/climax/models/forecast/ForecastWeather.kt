package com.herbivores.climax.models.forecast

import com.herbivores.climax.models.forecast.MutableList

data class ForecastWeather(
    val location: String,
    val forecast: ArrayList<List>
)