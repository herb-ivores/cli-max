package com.herbivores.climax.models.current

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Precipitation(
    @SerializedName("value")
    var value: Double? = null,
    @SerializedName("mode")
    var mode: String? = null,
)