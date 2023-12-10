package com.herbivores.climax.models.domain

enum class Direction(val symbol: String) {
    NORTH("N"),
    NORTH_EAST("NE"),
    EAST("E"),
    SOUTH_EAST("SE"),
    SOUTH("S"),
    SOUTH_WEST("SW"),
    WEST("W"),
    NORTH_WEST("NW"),
}

data class Wind(
    val speed: Double,
    val direction: Direction,
)
