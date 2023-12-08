package com.herbivores.climax.models.forecast

import com.google.gson.annotations.SerializedName

data class Rain (

    @SerializedName("3h" )
    var h : Double? = null

)