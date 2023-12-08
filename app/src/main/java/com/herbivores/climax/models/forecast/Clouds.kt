package com.herbivores.climax.models.forecast

import com.google.gson.annotations.SerializedName


data class Clouds (

    @SerializedName("all" )
    var all : Int? = null

)