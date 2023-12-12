package com.herbivores.climax.models.retrofit.forecast

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.herbivores.climax.models.domain.Direction
import com.herbivores.climax.models.domain.Wind

@Keep
data class Wind(
    @SerializedName("speed")
    var speed: Double? = null,
    @SerializedName("deg")
    var deg: Int? = null,
    @SerializedName("gust")
    var gust: Double? = null
){
    fun toWind() = Wind(
        speedMetersPerSecond = speed ?:0.0,
        direction = Direction(deg?:0),
    )
}