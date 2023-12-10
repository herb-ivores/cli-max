package com.herbivores.climax.models.current

import com.google.gson.annotations.SerializedName

data class Precipitation(
    @SerializedName("value")
    var value: Double? = null,
    @SerializedName("mode")
    var mode: String? = null,
)