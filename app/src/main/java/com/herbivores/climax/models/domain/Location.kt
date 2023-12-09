package com.herbivores.climax.models.domain

data class Location(
    val name: String,
    val latitude: Double,
    val longitude: Double,
) {
    companion object {
        val Samples = listOf(
            Location(
                name = "Angeles",
                latitude = 15.1451111,
                longitude = 120.5946389,
            ),
            Location(
                name = "San Luis",
                latitude = 15.012925,
                longitude = 120.840504,
            ),
            Location(
                name = "Floridablanca",
                latitude = 15.012925,
                longitude = 120.840504,
            ),
            Location(
                name = "Mabalacat",
                latitude = 15.1981126,
                longitude = 120.6004087,
            ),
            Location(
                name = "Manila",
                latitude = 14.5701612,
                longitude = 120.9857948,
            ),
            Location(
                name = "New York",
                latitude = 40.7629269,
                longitude = -73.9829357,
            ),
        )
    }
}