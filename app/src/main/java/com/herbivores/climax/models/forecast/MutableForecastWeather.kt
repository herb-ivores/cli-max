package com.herbivores.climax.models.forecast

import com.google.gson.annotations.SerializedName
import com.herbivores.climax.models.domain.ForecastWeather

data class MutableForecastWeather(
    @SerializedName("cod")
    var cod : String? = null,
    @SerializedName("message" )
    var message : Int? = null,
    @SerializedName("cnt")
    var cnt  : Int? = null,
    @SerializedName("list")
    var list : ArrayList<List> = arrayListOf(),
    @SerializedName("city")
    var city : City?  = City()
){
    fun toForecastWeather() = ForecastWeather(
        location = city?.name.orEmpty(),
        forecast = list.map { it.toDayWeather() },
    )
}