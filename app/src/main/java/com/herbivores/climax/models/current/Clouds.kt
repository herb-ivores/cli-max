package com.herbivores.climax.models.current

import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    var all : Int? = null,
)