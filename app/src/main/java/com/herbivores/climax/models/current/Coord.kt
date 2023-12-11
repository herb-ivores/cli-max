package com.herbivores.climax.models.current

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Coord(
    @SerializedName("lon")
    var lon: Double? = null,
    @SerializedName("lat")
    var lat: Double? = null,
)
