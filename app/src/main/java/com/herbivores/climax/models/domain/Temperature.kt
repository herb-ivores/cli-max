package com.herbivores.climax.models.domain

import kotlin.math.roundToInt

data class Temperature(val kelvin: Double) {
    val celsius = (kelvin - 273.15).roundToInt()
    val fahrenheit = ((kelvin - 273.15) * 9 / 5 + 32).roundToInt()
}

val Double.celsius get() = Temperature(this + 273.15)

val Int.celsius get() = toDouble().celsius