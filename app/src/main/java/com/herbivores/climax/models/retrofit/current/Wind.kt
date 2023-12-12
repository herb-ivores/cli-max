package com.herbivores.climax.models.retrofit.current

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Wind(
    @SerializedName("speed")
    var speed: Double? = null,
    @SerializedName("deg")
    var deg: Int? = null,
    @SerializedName("gust")
    var gust: Double? = null,
)
