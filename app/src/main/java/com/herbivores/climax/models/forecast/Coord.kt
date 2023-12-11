package com.herbivores.climax.models.forecast

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Coord(
    @SerializedName("lat")
    var lat: Double? = null,
    @SerializedName("lon")
    var lon: Double? = null
)