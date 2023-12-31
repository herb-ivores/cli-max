package com.herbivores.climax.models.retrofit.current

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Main(
    @SerializedName("temp")
    var temp: Double? = null,
    @SerializedName("feels_like")
    var feelsLike: Double? = null,
    @SerializedName("pressure")
    var pressure: Int? = null,
    @SerializedName("humidity")
    var humidity: Int? = null,
    @SerializedName("temp_min")
    var tempMin: Double? = null,
    @SerializedName("temp_max")
    var tempMax: Double? = null,
    @SerializedName("sea_level")
    var seaLevel: Int? = null,
    @SerializedName("grnd_level")
    var grndLevel: Int? = null,
)
