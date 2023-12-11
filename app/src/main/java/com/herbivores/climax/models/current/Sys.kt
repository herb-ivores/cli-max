package com.herbivores.climax.models.current

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Sys(
    @SerializedName("type")
    var type: Int? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("country")
    var country: String? = null,
    @SerializedName("sunrise")
    var sunrise: Long? = null,
    @SerializedName("sunset")
    var sunset: Long? = null,
)