package com.herbivores.climax.models.retrofit.forecast

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Rain (
    @SerializedName("3h" )
    var h : Double? = null,
)