package com.herbivores.climax.repositories

import com.herbivores.climax.constants.WeatherApi
import com.herbivores.climax.models.forecast.MutableForecastWeather
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastWeather {
    @GET(WeatherApi.FORECAST_WEATHER)
    suspend fun getForecastWeather(
        @Query("lat") lat : Double,
        @Query("lon") lon : Double,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): MutableForecastWeather
}