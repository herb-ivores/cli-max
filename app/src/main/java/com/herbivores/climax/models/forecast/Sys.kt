package com.herbivores.climax.models.forecast

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Sys (
    @SerializedName("pod")
    var pod: String? = null,
)