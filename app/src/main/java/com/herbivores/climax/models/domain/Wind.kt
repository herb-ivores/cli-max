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

fun Direction(degrees: Int): Direction {
    val directions = Direction.entries
    val index = degrees / 45 % directions.size
    return directions[index]
}

data class Wind(
    val speedMetersPerSecond: Double,
    val direction: Direction,
)