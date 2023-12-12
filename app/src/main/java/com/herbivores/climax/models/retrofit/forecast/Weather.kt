package com.herbivores.climax.models.retrofit.forecast

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Weather(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("main")
    var main: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("icon")
    var icon: String? = null,
)