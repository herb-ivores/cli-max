package com.herbivores.climax.repositories

import com.herbivores.climax.apiclient.WeatherApiClient
import com.herbivores.climax.constants.WeatherApi
import com.herbivores.climax.models.MutableCurrentWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherRepository {
    fun getCurrentWeather(latitude: Double, longitude: Double): Flow<MutableCurrentWeather> = flow {
        emit(
            WeatherApiClient.retrofit.create(Weather::class.java)
                .getCurrentWeather(latitude, longitude, WeatherApi.KEY, "metric")
        )
    }.flowOn(Dispatchers.IO)
}