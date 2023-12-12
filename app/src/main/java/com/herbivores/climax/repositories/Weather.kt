package com.herbivores.climax.repositories

import com.herbivores.climax.constants.WeatherApi
import com.herbivores.climax.models.retrofit.current.MutableCurrentWeather
import retrofit2.http.GET
import retrofit2.http.Query

interface Weather {
    @GET(WeatherApi.CURRENT_WEATHER)
    suspend fun getCurrentWeather(
        @Query("lat") lat : Double,
        @Query("lon") lon : Double,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): MutableCurrentWeather
}

